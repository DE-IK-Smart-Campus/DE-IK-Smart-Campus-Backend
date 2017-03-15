package hu.unideb.smartcampus.persistence.repository;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NULL_VALUE_ERROR_MESSAGE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_FRIDAY;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_FRIDAY_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_MONDAY;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_MONDAY_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.FRIDAY_END_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.FRIDAY_START_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.INSTRUCTOR_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.MONDAY_END_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.MONDAY_START_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.NAME;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.SUBJECT_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.SUBJECT_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.util.FromToDateUtil;

/**
 * Test for {@link InstructorRepository}.
 */
public class InstructorRepositoryIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  /**
   * Sample subject.
   */
  private static final SubjectDetailsEntity SAMPLE_SUBJECT =
      SubjectDetailsEntity.builder().id(SUBJECT_ID).subjectName(SUBJECT_NAME).build();

  /**
   * Friday consulting date entity.
   */
  private final ConsultingDateEntity fridayConsultingDate = createFridayConsultingDate();

  /**
   * Friday consulting date entity.
   */
  private final ConsultingDateEntity mondayConsultingDate = createMondayConsultingDate();

  /**
   * Instructor.
   */
  private final InstructorEntity instructor =
      InstructorEntity.builder().id(INSTRUCTOR_ID).name(NAME).subjects(Sets.newSet(SAMPLE_SUBJECT))
          .consultingDates(Sets.newSet(fridayConsultingDate)).build();

  /**
   * InstructorRepository.
   */
  @Autowired
  private InstructorRepository instructorRepository;


  private ConsultingDateEntity createMondayConsultingDate() {
    return ConsultingDateEntity.builder().id(CONSULTING_DATE_MONDAY_ID).date(CONSULTING_DATE_MONDAY)
        .sum(0).fromToDate(getMonday()).build();
  }

  private FromToDateEmbeddedEntity getMonday() {
    return FromToDateUtil.createEntity(MONDAY_START_DATE, MONDAY_END_DATE);
  }

  private ConsultingDateEntity createFridayConsultingDate() {
    return ConsultingDateEntity.builder().id(CONSULTING_DATE_FRIDAY_ID).date(CONSULTING_DATE_FRIDAY)
        .sum(0).fromToDate(getFriday()).build();
  }

  private FromToDateEmbeddedEntity getFriday() {
    return FromToDateUtil.createEntity(FRIDAY_START_DATE, FRIDAY_END_DATE);
  }

  /**
   * Test for "null" username.
   */
  @Test
  public void findByUsernameShouldReturnNullWhenParameterUsernameIsNull() {
    // Given

    // When
    final InstructorEntity result = this.instructorRepository.findByName(null);

    // Then
    assertThat(ASSERTION_NULL_VALUE_ERROR_MESSAGE, result, nullValue());
  }

  /**
   * Test for non-existing username.
   */
  @Test
  public void findByUsernameShouldReturnNullWhenParameterUsernameDoesNotExist() {
    // Given
    final String nonExistentUsername = "Non existent username";

    // When
    final InstructorEntity result = this.instructorRepository.findByName(nonExistentUsername);

    // Then
    assertThat(ASSERTION_NULL_VALUE_ERROR_MESSAGE, result, nullValue());
  }

  /**
   * Test for existing username.
   */
  @Test
  public void findByUsernameShouldReturnExistentUserEntityWhenParameterUsernameExists() {
    // Given

    // When
    final InstructorEntity result = this.instructorRepository.findByName(NAME);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(instructor));
  }

  /**
   * Test get instructor consulting hours.
   */
  @Test
  public void getInstructorConsultingHoursByInstructorNameShouldReturnConsultingDates() {
    // Given

    // When
    Set<ConsultingDateEntity> result =
        instructorRepository.getInstructorConsultingHoursByInstructorName(NAME);

    // Then
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result,
        equalTo(Sets.newSet(fridayConsultingDate, mondayConsultingDate)));

  }

  /**
   * Test get instructors by subject name.
   */
  @Test
  public void getInstructorsBySubjectNameShouldReturnNonEmptySet() {
    // Given

    // When
    Set<InstructorEntity> result = instructorRepository.getInstructorsBySubjectName("AI");

    // Then
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(Sets.newSet(instructor)));
  }

  /**
   * Test get instructors consulting dates after given date.
   */
  @Test
  public void getInstructorConsultingDatesByIdAndGivenDateShouldReturnConsultingDatesAfterGivenDate() {
    // Given
    Calendar from = Calendar.getInstance();
    from.set(2017, 2, 7);

    Calendar to = Calendar.getInstance();
    to.set(2017, 2, 14);

    // When
    Set<ConsultingDateEntity> consultingDates = instructorRepository
        .getInstructorConsultingDatesByIdAndGivenDate(1L, from.getTime(), to.getTime());

    // Then
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, consultingDates,
        equalTo(Sets.newSet(fridayConsultingDate)));
  }
}
