package hu.unideb.smartcampus.persistence.repository;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NULL_VALUE_ERROR_MESSAGE;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.PASSWORD_ADMIN;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.USERNAME_ADMIN;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.USER_ID_ADMIN;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
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
  private final SubjectDetailsEntity sampleSubject = SubjectDetailsEntity.builder()
      .subjectName("AI").subjectType("LABORATORY").startPeriod(LocalDate.of(2000, 02, 01))
      .endPeriod(LocalDate.of(2000, 05, 31)).teacherNames(Collections.emptyList()).build();

  /**
   * Admin user.
   */
  private final UserEntity adminUser =
      UserEntity.builder().id(USER_ID_ADMIN).username(USERNAME_ADMIN).password(PASSWORD_ADMIN)
          .actualSubjects(Arrays.asList(sampleSubject)).role(Role.ADMIN).build();

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
    // assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(adminUser));
  }

  /**
   * Test get subject's by user's username.
   */
  @Test
  public void getSubjectsByUsernameShouldReturnNonEmptySet() {
    // Given

    // When
    final Set<SubjectDetailsEntity> result =
        this.userRepository.getSubjectsByUsername(USERNAME_ADMIN);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    // assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result,
    // equalTo(Sets.newHashSet(sampleSubject)));
  }

  /**
   * Test get subject's by user id.
   */
  @Test
  public void getSubjectsByIdShouldReturnNonEmptySet() {
    // Given

    // When
    final Set<SubjectDetailsEntity> result =
        this.userRepository.getSubjectsByUsername(USERNAME_ADMIN);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    // assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result,
    // equalTo(Sets.newHashSet(sampleSubject)));
  }

  /**
   * Test get user id by username.
   */
  @Test
  public void getIdByUsername() throws Exception {
    // Given

    // When

    // Then
    Long idByUsername = userRepository.getIdByUsername(USERNAME_ADMIN);
    
    Assert.assertEquals(USER_ID_ADMIN, idByUsername.longValue());
  }
  
  /**
   * Test get user subjects within range.
   */
  @Test
  public void getSubjectsWithinRangeByUsername() throws Exception {
    Set<SubjectDetailsEntity> subjects = userRepository.getSubjectsWithinRangeByUsername(USERNAME_ADMIN, LocalDate.of(2016, 1, 1), LocalDate.of(2016, 05, 31));
    
  }
  
  /**
   * Test get muc chat list. 
   */
  @Test
  public void getMucChatListByUsername() throws Exception {
    List<String> mucList = userRepository.getMucChatListByUsername(USERNAME_ADMIN);
    Assert.assertFalse(mucList.isEmpty());
    Assert.assertEquals(3, mucList.size());
  }
  

  /**
   * Test get single chat list. 
   */
  @Test
  public void getSingleChatListByUsername() throws Exception {
    List<String> singleChat = userRepository.getSingleChatListByUsername(USERNAME_ADMIN);
    Assert.assertFalse(singleChat.isEmpty());
    Assert.assertEquals(2, singleChat.size());
  }
}
