package hu.unideb.smartcampus.service.api;

import javax.annotation.Resource;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaCollector;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * Service implementation.
 */
@Service
public class ConsultingHoursServiceImpl implements ConsultingHoursService {

  /**
   * Delimiter.
   */
  private static final String AT = "@";

  /**
   * Resource.
   */
  private static final String RESOURCE = "/Smartcampus";

  /**
   * Smartcampus username.
   */
  private static final String SMARTCAMPUS = "smartcampus";

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHoursServiceImpl.class);

  /**
   * Domain for smartcampus user.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  private String domain;

  /**
   * Ejabberd user.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  /**
   * {@inheritDoc}.
   */
  @Override
  public SubjectsIqRequest getSubjects() {
    LOGGER.info("GetSubjects()");
    SubjectsIqRequest resultIq;
    AbstractXMPPConnection connection = ejabberdUser.getConnection();
    SubjectsIqRequest iq = new SubjectsIqRequest();
    iq.setType(Type.get);
    iq.setFrom(connection.getUser());
    setSmartcampusUser(iq);
    iq.setStudent(getUser(connection));
    resultIq = getResult(connection, iq);
    if (resultIq != null) {
      LOGGER.debug("The size of the result subjects is:{}", resultIq.getSubjects().size());
    }
    return resultIq;
  }


  private SubjectsIqRequest getResult(AbstractXMPPConnection connection,
      SubjectsIqRequest iq) {
    SubjectsIqRequest resultIq;
    try {
      StanzaCollector collector = connection.createStanzaCollectorAndSend(iq);
      resultIq = collector.nextResult(10000);
    } catch (NotConnectedException | InterruptedException e) {
      LOGGER.error("Error while sending IQ", e);
      throw new RuntimeException(e);
    }
    return resultIq;
  }


  private void setSmartcampusUser(SubjectsIqRequest iq) {
    try {
      iq.setTo(JidCreate.from(getSmartcampusUser()));
    } catch (XmppStringprepException e1) {
      throw new RuntimeException(e1);
    }
  }


  private String getSmartcampusUser() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(SMARTCAMPUS)
        .append(AT)
        .append(domain)
        .append(RESOURCE);
    return stringBuilder.toString();
  }


  private String getUser(AbstractXMPPConnection connection) {
    return connection.getUser().toString().split(AT)[0];
  }

}
