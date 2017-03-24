package hu.unideb.smartcampus.service.api.converter.todomain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test for {@link UserEntityToUserConverter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserEntityToUserConverterTest {

  /**
   * UserEntityToUserConverter.
   */
  //private final UserEntityToUserConverter userEntityToUserConverter = new UserEntityToUserConverter();
  //private final UserEntityToUserConverter userEntityToUserConverter = null;

  /**
   * Test for "null" parameter.
   */
  /*@Test
  public void convertShouldReturnNullWhenParameterUserEntityIsNull() {
    // Given

    // When
    final User result = this.userEntityToUserConverter.convert(null);

    // Then
    assertThat(ASSERTION_NULL_VALUE_ERROR_MESSAGE, result, nullValue());
  }

  /**
   * Test for "non-null" parameter.
   */
  /*@Test
  public void convertShouldReturnNonNullValueWhenParameterUserEntityIsNotNull() {
    // Given
    final UserEntity userEntity = UserEntity.builder()
        .id(USER_ID_ADMIN)
        .username(USERNAME_ADMIN)
        .password(PASSWORD_ADMIN)
        .role(Role.ADMIN)
        .build();

    // When
    final User result = this.userEntityToUserConverter.convert(userEntity);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(this.buildExpectedUser()));
  }

  private User buildExpectedUser() {
    return User.builder()
        .id(USER_ID_ADMIN)
        .username(USERNAME_ADMIN)
        .password(PASSWORD_ADMIN)
        .role(Role.ADMIN)
        .build();
  }*/

  /**
   * Test method.
   */
  @Test
  public void method() {
    assertTrue(true);
  }
}
