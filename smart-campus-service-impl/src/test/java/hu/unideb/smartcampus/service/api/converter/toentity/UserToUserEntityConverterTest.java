package hu.unideb.smartcampus.service.api.converter.toentity;

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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.enumeration.Role;

/**
 * Test for {@link UserToUserEntityConverter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserToUserEntityConverterTest {

  /**
   * UserToUserEntityConverter.
   */
  private final UserToUserEntityConverter userToUserEntityConverter = new UserToUserEntityConverter();

  /**
   * Test for "null" parameter.
   */
  @Test
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
  @Test
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
}
