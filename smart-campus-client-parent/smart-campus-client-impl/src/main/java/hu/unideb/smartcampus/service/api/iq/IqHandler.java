package hu.unideb.smartcampus.service.api.iq;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaCollector;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * An abstract class to represent an IQ handler with its necessary fields and methods.
 */
public abstract class IqHandler<T extends BaseSmartCampusIqRequest> {

  /**
   * Delimiter.
   */
  protected static final String AT = "@";

  /**
   * Resource.
   */
  private static final String RESOURCE = "/Smartcampus";

  /**
   * Smartcampus username.
   */
  private static final String SMARTCAMPUS = "smartcampus";

  /**
   * Connection.
   */
  protected AbstractXMPPConnection connection;

  /**
   * Domain string.
   */
  protected String domain;

  /**
   * IQ request.
   */
  protected T iq;

  /**
   * Get the result of the IQ request.
   * @return IQ result.
   */
  public T getResult() {
    T resultIq;
    try {
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      resultIq = collector.nextResult(10000);
    } catch (SmackException.NotConnectedException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return resultIq;
  }

  /**
   * Set the Smartcampus user of the IQ request.
   * @param iq IQ request.
   */
  protected void setSmartcampusUser(BaseSmartCampusIqRequest iq) {
    try {
      iq.setTo(JidCreate.from(getSmartcampusUser()));
    } catch (XmppStringprepException e1) {
      throw new RuntimeException(e1);
    }
  }

  /**
   * Get the Smartcampus user.
   * @return Smartcampus user.
   */
  protected String getSmartcampusUser() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(SMARTCAMPUS)
        .append(AT)
        .append(domain)
        .append(RESOURCE);
    return stringBuilder.toString();
  }

  /**
   * Get the user of the XMPP connection.
   * @param connection XMPP connection.
   * @return User string.
   */
  protected String getUser(AbstractXMPPConnection connection) {
    return connection.getUser().toString().split(AT)[0];
  }

}
