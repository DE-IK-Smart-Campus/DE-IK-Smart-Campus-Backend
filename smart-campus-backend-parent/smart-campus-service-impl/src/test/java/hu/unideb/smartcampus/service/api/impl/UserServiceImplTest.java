package hu.unideb.smartcampus.service.api.impl;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_IS_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.PASSWORD_ADMIN;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.USERNAME_ADMIN;
import static hu.unideb.smartcampus.shared.test.property.UserTestProperty.USER_ID_ADMIN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.enumeration.Role;

/**
 * Test for {@link UserService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

  /**
   * UserRepository mock.
   */
  private final UserRepository userRepository = mock(UserRepository.class);
  /**
   * ConversionService mock.
   */
  private final ConversionService conversionService = mock(ConversionService.class);

  /**
   * UserService with UserRepository and ConversionService mocks.
   */
  private final UserService userService = new UserServiceImpl(this.userRepository, this.conversionService);

  /**
   * Test for "null" username.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getByUsernameShouldThrowIllegalArgumentExceptionWhenParameterUsernameIsNull() {
    // Given

    // When
    this.userService.getByUsername(null);

    // Then
  }

  /**
   * Test for non-existing username.
   */
  @Test
  public void getByUsernameShouldReturnEmptyOptionalWhenParameterUsernameDoesNotExist() {
    // Given
    final String nonExistentUsername = "Non existent username";

    given(this.userRepository.findByUsername(nonExistentUsername)).willReturn(null);
    given(this.conversionService.convert(null, User.class)).willReturn(null);

    // When
    final Optional<User> result = this.userService.getByUsername(nonExistentUsername);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_IS_ERROR_MESSAGE, result.isPresent(), is(false));

    then(this.userRepository).should().findByUsername(nonExistentUsername);
    then(this.conversionService).should().convert(null, User.class);
    verifyNoMoreInteractions(this.userRepository, this.conversionService);
  }

  /**
   * Test for existing username.
   */
  @Test
  public void getByUsernameShouldReturnNonEmptyOptionalWhenParameterUsernameExists() {
    // Given
    final UserEntity userEntity = this.buildUserEntity();
    final User expectedUser = this.buildExpectedUser();

    given(this.userRepository.findByUsername(USERNAME_ADMIN)).willReturn(userEntity);
    given(this.conversionService.convert(userEntity, User.class)).willReturn(expectedUser);

    // When
    final Optional<User> result = this.userService.getByUsername(USERNAME_ADMIN);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_IS_ERROR_MESSAGE, result.isPresent(), is(true));
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result.get(), equalTo(expectedUser));

    then(this.userRepository).should().findByUsername(USERNAME_ADMIN);
    then(this.conversionService).should().convert(userEntity, User.class);
    verifyNoMoreInteractions(this.userRepository, this.conversionService);
  }

  private UserEntity buildUserEntity() {
    return UserEntity.builder()
        .id(USER_ID_ADMIN)
        .username(USERNAME_ADMIN)
        .password(PASSWORD_ADMIN)
        .role(Role.ADMIN)
        .build();
  }

  private User buildExpectedUser() {
    return User.builder()
        .id(USER_ID_ADMIN)
        .username(USERNAME_ADMIN)
        .password(PASSWORD_ADMIN)
        .role(Role.ADMIN)
        .build();
  }
}
