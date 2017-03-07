package hu.unideb.smartcampus.persistence.repository;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NULL_VALUE_ERROR_MESSAGE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.INSTRUCTOR_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEntity;

/**
 * Test for {@link InstructorRepository}.
 */
public class InstructorRepositoryIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  /**
   * Sample subject.
   */
  private final SubjectEntity sampleSubject = SubjectEntity.builder().id(1L).name("AI").build();

  /**
   * Friday consulting date entity.
   */
  private ConsultingDateEntity fridayConsultingDate = createFridayConsultingDate();

  /**
   * Friday consulting date entity.
   */
  private ConsultingDateEntity mondayConsultingDate = createMondayConsultingDate();

  /**
   * Instructor.
   */
  private final InstructorEntity instructor =
      InstructorEntity.builder().id(INSTRUCTOR_ID).name(NAME).subjects(Sets.newSet(sampleSubject))
          .consultingDates(Sets.newSet(fridayConsultingDate)).build();

  /**
   * InstructorRepository.
   */
  @Autowired
  private InstructorRepository instructorRepository;


  private ConsultingDateEntity createMondayConsultingDate() {
    return ConsultingDateEntity.builder().id(2L).date("Monday 08-10").fromToDate(getMonday())
        .build();
  }

  private FromToDateEmbeddedEntity getMonday() {
    // 2017-03-07 14:00:00
    Calendar from = Calendar.getInstance();
    from.set(2017, 2, 6, 8, 0, 0);

    // 2017-03-07 16:00:00
    Calendar to = Calendar.getInstance();
    to.set(2017, 2, 6, 10, 0, 0);

    Timestamp fromDate = new Timestamp(from.getTime().getTime());
    fromDate.setNanos(0);
    Timestamp toDate = new Timestamp(to.getTime().getTime());
    toDate.setNanos(0);

    return FromToDateEmbeddedEntity.builder().fromDate(fromDate).toDate(toDate).build();
  }

  private ConsultingDateEntity createFridayConsultingDate() {
    return ConsultingDateEntity.builder().id(1L).date("Friday 14-16").fromToDate(getFriday())
        .build();
  }

  private FromToDateEmbeddedEntity getFriday() {
    // 2017-03-07 14:00:00
    Calendar from = Calendar.getInstance();
    from.set(2017, 2, 10, 14, 0, 0);

    // 2017-03-07 16:00:00
    Calendar to = Calendar.getInstance();
    to.set(2017, 2, 10, 16, 0, 0);

    Timestamp fromDate = new Timestamp(from.getTime().getTime());
    fromDate.setNanos(0);
    Timestamp toDate = new Timestamp(to.getTime().getTime());
    toDate.setNanos(0);

    return FromToDateEmbeddedEntity.builder().fromDate(fromDate).toDate(toDate).build();
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
    from.set(2017, 2, 6);

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
