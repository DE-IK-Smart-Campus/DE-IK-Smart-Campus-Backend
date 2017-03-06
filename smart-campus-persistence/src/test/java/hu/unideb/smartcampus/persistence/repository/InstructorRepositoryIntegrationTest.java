package hu.unideb.smartcampus.persistence.repository;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NULL_VALUE_ERROR_MESSAGE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.INSTRUCTOR_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEntity;

/**
 * Test for {@link InstructorRepository}.
 */
public class InstructorRepositoryIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  /**
   * Consulting entity.
   */
  private final ConsultingDateEntity consultingEntity =
      ConsultingDateEntity.builder().id(CONSULTING_DATE_ID).date(CONSULTING_DATE).build();


  /**
   * Sample subject.
   */
  private final SubjectEntity sampleSubject = SubjectEntity.builder().id(1L).name("AI").build();

  /**
   * Instructor.
   */
  private final InstructorEntity instructor = InstructorEntity.builder().id(INSTRUCTOR_ID)
      .name(NAME).consultingDates(Sets.newSet(consultingEntity))
      .subjects(Sets.newSet(sampleSubject)).build();

  /**
   * InstructorRepository.
   */
  @Autowired
  private InstructorRepository instructorRepository;

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
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(Sets.newSet(consultingEntity)));

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
}
