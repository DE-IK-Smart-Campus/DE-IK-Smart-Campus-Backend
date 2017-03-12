package hu.unideb.smartcampus.shared.requestmessages;

import lombok.Data;

/**
 * Base request type for incoming Android messages.
 *
 */
@Data
public class BaseRequest implements BaseRequestType {

  /**
   * Message type.
   */
  protected final String messageType;
}
