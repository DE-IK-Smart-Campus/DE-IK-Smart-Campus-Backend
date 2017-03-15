package hu.unideb.smartcampus.persistence.repository;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NULL_VALUE_ERROR_MESSAGE;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.PASSWORD_ADMIN;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.USERNAME_ADMIN;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.USER_ID_ADMIN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.shared.enumeration.Role;

/**
 * Test for {@link UserRepository}.
 */
public class UserRepositoryIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  /**
   * Sample subject.
   */
  private final SubjectDetailsEntity sampleSubject = SubjectDetailsEntity.builder().id(1L).subjectName("AI").build();

  /**
   * Admin user.
   */
  private final UserEntity adminUser =
      UserEntity.builder().id(USER_ID_ADMIN).username(USERNAME_ADMIN).password(PASSWORD_ADMIN)
          .actualSubjects(Sets.newSet(sampleSubject)).role(Role.ADMIN).build();

  /**
   * UserRepository.
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * Test for "null" username.
   */
  @Test
  public void findByUsernameShouldReturnNullWhenParameterUsernameIsNull() {
    // Given

    // When
    final UserEntity result = this.userRepository.findByUsername(null);

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
    final UserEntity result = this.userRepository.findByUsername(nonExistentUsername);

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
    final UserEntity result = this.userRepository.findByUsername(USERNAME_ADMIN);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(adminUser));
  }

  /**
   * Test get subject's by user's username.
   */
  @Test
  public void getSubjectsByUsernameShouldReturnNonEmptySet() {
    // Given

    // When
    final Set<SubjectDetailsEntity> result = this.userRepository.getSubjectsByUsername(USERNAME_ADMIN);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(Sets.newSet(sampleSubject)));
  }

  /**
   * Test get subject's by user id.
   */
  @Test
  public void getSubjectsByIdShouldReturnNonEmptySet() {
    // Given

    // When
    final Set<SubjectDetailsEntity> result = this.userRepository.getSubjectsByUserId(USER_ID_ADMIN);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(Sets.newSet(sampleSubject)));
  }
}
