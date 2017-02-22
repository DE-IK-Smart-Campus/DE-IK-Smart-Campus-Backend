package hu.unideb.smartcampus.shared.exception;

/**
 * Parent exception of every exception which handles XMPP exception.
 *
 *
 */
public class XmppException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs an empty XmppException.
   */
  public XmppException() {
    super();
  }

  /**
   * Constructs a XmppException with message.
   */
  public XmppException(String message) {
    super(message);
  }

  /**
   * Constructs a XmppException with message and cause.
   */
  public XmppException(String message, Throwable cause) {
    super(message, cause);
  }

}
