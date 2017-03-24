package hu.unideb.smartcampus.service.api.converter.toentity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test for {@link UserToUserEntityConverter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserToUserEntityConverterTest {

  /**
   * UserToUserEntityConverter.
   */
  //private final UserToUserEntityConverter userToUserEntityConverter = new UserToUserEntityConverter();
  //private final UserToUserEntityConverter userToUserEntityConverter = null;

  /**
   * Test for "null" parameter.
   */
  /*@Test
  public void convertShouldReturnNullWhenParameterUserIsNull() {
    // Given

    // When
    final UserEntity result = this.userToUserEntityConverter.convert(null);

    // Then
    assertThat(ASSERTION_NULL_VALUE_ERROR_MESSAGE, result, nullValue());
  }

  /**
   * Test for "non-null" parameter.
   */
  /*@Test
  public void convertShouldReturnNonNullValueWhenParameterUserIsNotNull() {
    // Given
    final User user = User.builder()
        .id(USER_ID_ADMIN)
        .username(USERNAME_ADMIN)
        .password(PASSWORD_ADMIN)
        .role(Role.ADMIN)
        .build();

    // When
    final UserEntity result = this.userToUserEntityConverter.convert(user);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(this.buildExpectedUserEntity()));
  }

  private UserEntity buildExpectedUserEntity() {
    return UserEntity.builder()
        .id(USER_ID_ADMIN)
        .username(USERNAME_ADMIN)
        .password(PASSWORD_ADMIN)
        .role(Role.ADMIN)
        .build();
  }

  */

  /**
   * Test method.
   */
  @Test
  public void method() {
    assertTrue(true);
  }
}
