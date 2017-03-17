package hu.unideb.smartcampus.service.api.impl;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.repository.ConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.OfficeHourGeneratorService;
import hu.unideb.smartcampus.service.api.converter.todomain.SubjectEntityToSubjectConverter;
import hu.unideb.smartcampus.service.api.converter.toentity.ConsultingDateToConsultingDateEntityConverter;
import hu.unideb.smartcampus.service.api.converter.toentity.FromToDateToFromToDateEntityConverter;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.FromToDate;
import hu.unideb.smartcampus.service.api.domain.Subject;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;

/**
 * Test for {@link ConsultingHourService}.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD"})
public class ConsultingHourServiceImplTest {

  /**
   * To timestamp date.
   */
  private static final Timestamp TO_DATE = new Timestamp(15);

  /**
   * From timestamp date.
   */
  private static final Timestamp FROM_DATE = new Timestamp(10);

  /**
   * Date in string.
   */
  private static final String DATE_IN_STRING = "Monday 12:00-14:00";

  /**
   * Subject name.
   */
  private static final String AI = "AI";

  /**
   * Result subject.
   */
  private static final Subject SUBJECT_DOMAIN = Subject.builder().name(AI).build();

  /**
   * Expected set of subjects.
   */
  private static final Set<Subject> EXPECTED_DOMAIN_SET = Sets.newSet(SUBJECT_DOMAIN);

  /**
   * Subject entity.
   */
  private static final SubjectEntity SUBJECT_ENTITY =
      SubjectEntity.builder().id(1L).name(AI).build();

  /**
   * Set of entities.
   */
  private static final Set<SubjectEntity> RESULT_ENTITY_SET = Sets.newSet(SUBJECT_ENTITY);

  /**
   * Instructor id.
   */
  private static final Long INSTRUCTOR_ID = 1L;

  /**
   * To date in string.
   */
  private static final String TO_IN_STRING = "14:00";

  /**
   * From date in string.
   */
  private static final String FROM_IN_STRING = "12:00";

  /**
   * Generated hours.
   */
  private static final Integer GENERATED_HOURS = 14;

  /**
   * Office hours.
   */
  private static final List<OfficeHour> OFFICEHOURS = Arrays.asList(
      OfficeHour.builder().day(DayOfWeek.MONDAY).from(FROM_IN_STRING).to(TO_IN_STRING).build());

  /**
   * Intervall.
   */
  private static final OfficeHourIntervall INTERVALL = OfficeHourIntervall.builder()
      .fromDate(Timestamp.valueOf(LocalDate.of(2017, 2, 20).atStartOfDay()))
      .toDate(Timestamp.valueOf(LocalDate.of(2017, 5, 26).atStartOfDay())).build();

  /**
   * Consulting dates.
   */
  private static final List<ConsultingDate> CONSULTING_DATES =
      Arrays.asList(ConsultingDate.builder().date(DATE_IN_STRING).sum(0).build());

  /**
   * Instructor entity.
   */
  private static final InstructorEntity INSTRUCTOR_ENTITY =
      InstructorEntity.builder().id(INSTRUCTOR_ID).consultingDates(Sets.newSet()).build();

  /**
   * From to date source.
   */
  private static final FromToDate FROM_TO_DATE_SOURCE =
      FromToDate.builder().fromDate(FROM_DATE).toDate(TO_DATE).build();


  /**
   * UserService with UserRepository and ConversionService mocks.
   */
  @InjectMocks
  private ConsultingHourServiceImpl consultingHoursService;

  /**
   * UserRepository mock.
   */
  @Mock
  private UserRepository userRepository;

  /**
   * SubjectEntityToSubjectConverter implementation.
   */
  @Spy
  private SubjectEntityToSubjectConverter conversionService;

  /**
   * Office hour generator service.
   */
  @Mock
  private OfficeHourGeneratorService officeHourGeneratorService;

  /**
   * Consulting date repository.
   */
  @Mock
  private ConsultingDateRepository consultingDateRepository;

  /**
   * Instructor repository.
   */
  @Mock
  private InstructorRepository instructorRepository;

  /**
   * Converter spy.
   */
  @Mock
  private ConsultingDateToConsultingDateEntityConverter converter;

  /**
   * From to date converter.
   */
  @Mock
  private FromToDateToFromToDateEntityConverter fromToDateConverter;

  /**
   * Test subject list is empty.
   */
  @Test
  public void getSubjectsByUserIdShouldReturnEmptySet() {
    // given

    // when
    given(userRepository.getSubjectsByUserId(1L)).willReturn(Collections.emptySet());

    // then
    Set<Subject> subjects = consultingHoursService.getSubjectsByUserId(1L);

    Assert.assertTrue(subjects.isEmpty());
  }


  /**
   * Test should return user's subjects.
   */
  @Test
  public void getSubjectsByUserIdShouldReturnUsersSubjects() {
    // given

    // when
    given(userRepository.getSubjectsByUserId(1L)).willReturn(RESULT_ENTITY_SET);

    // then
    Set<Subject> subjects = consultingHoursService.getSubjectsByUserId(1L);

    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, subjects, equalTo(EXPECTED_DOMAIN_SET));
  }

  /**
   * Test generate office hours for instructor in given intervall.
   */
  @Test
  public void generateOfficeHoursForInstructor() {
    // given

    // when
    Mockito.when(officeHourGeneratorService.generateOfficeHoursInIntervall(INTERVALL, OFFICEHOURS))
        .thenReturn(CONSULTING_DATES);
    Mockito.when(instructorRepository.findOne(INSTRUCTOR_ID)).thenReturn(INSTRUCTOR_ENTITY);
    Mockito.when(fromToDateConverter.convert(FROM_TO_DATE_SOURCE))
        .thenReturn(FromToDateEmbeddedEntity.builder().fromDate(FROM_DATE).toDate(TO_DATE).build());
    // then
    Integer generateOfficeHoursForInstructor = consultingHoursService
        .generateOfficeHoursForInstructor(INSTRUCTOR_ID, OFFICEHOURS, INTERVALL);

    Assert.assertEquals(Integer.valueOf(1), generateOfficeHoursForInstructor);
  }
}
