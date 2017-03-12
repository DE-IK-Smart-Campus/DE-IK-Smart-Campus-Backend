package hu.unideb.smartcampus.xmpp.exception;

import lombok.Builder;
import lombok.Data;

/**
 * This is not a real Exception, this is just a response type which should be sent as JSON when some
 * kind of real Exception thrown while the XMPP message are being processed.
 */
@Data
public class ChatResponseException {

  /**
   * Exception message.
   */
  private final String message;

  /**
   * Exception's class in string.
   */
  private final String exceptionType;

  /**
   * Constructs a ChatResponseException instance.
   */
  @Builder
  public ChatResponseException(String message, String exceptionType) {
    this.message = message;
    this.exceptionType = exceptionType;
  }
  
  
}
