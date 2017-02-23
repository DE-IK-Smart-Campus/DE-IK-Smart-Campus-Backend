package h.unideb.smartcampus.shared.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Assertion error message provider.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AssertionErrorMessage {

  /**
   * Message for nullValue() assertion.
   */
  public static final String ASSERTION_NULL_VALUE_ERROR_MESSAGE = "Actual result should be null";

  /**
   * Message for notNullValue() assertion.
   */
  public static final String ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE = "Actual result should not be null";

  /**
   * Message for equalTo() assertion.
   */
  public static final String ASSERTION_EQUAL_TO_ERROR_MESSAGE = "Actual and expected result should be the same";

  /**
   * Message for is() assertion.
   */
  public static final String ASSERTION_IS_ERROR_MESSAGE = "Actual and expected result should be the same";
}
