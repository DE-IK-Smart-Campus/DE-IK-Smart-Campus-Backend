package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.ChangeAttendanceIqProvider;

/**
 * Change attendance IQ provider test.
 */
public class ChangeAttendanceIqParserTest extends AbstractParserTest {

  private static final Long APPOINTMENT_ID = 2L;
  private static final String STUDENT = "Emily Stock";
  private static final String PRESENT = "true";

  @Test
  public void parseTimeWithIntrospectionTest() throws Exception {
    // @formatter:off
      final String request =
      "<iq type='set'"
        + "from='romeo@montague.net/orchard'"
        + "to='juliet@capulet.com/balcony'"
        + "id='time_1'>"
        + "<changeAttendance xmlns='http://inf.unideb.hu/smartcampus/'>"
        + "<student>"+STUDENT+"</student>"
        + "<appointmentId>"+APPOINTMENT_ID+"</appointmentId>"
        + "<present>"+PRESENT+"</present>"
        + "</changeAttendance>"
        + "</iq>";
      // @formatter:on
    ProviderManager.addIQProvider(ChangeAttendanceIqRequest.ELEMENT,
        BaseSmartCampusIqRequest.BASE_NAMESPACE, getProvider());
    IQ iqRequest = (IQ) PacketParserUtils.parseStanza(request);
    Assert.assertTrue(iqRequest instanceof ChangeAttendanceIqRequest);
    Assert.assertEquals(STUDENT, ((ChangeAttendanceIqRequest) iqRequest).getStudent());
    Assert.assertEquals(APPOINTMENT_ID.longValue(),
        ((ChangeAttendanceIqRequest) iqRequest).getAppointmentId());
    Assert.assertTrue(((ChangeAttendanceIqRequest) iqRequest).getPresent());
  }


  @Override
  public String getElement() {
    return ChangeAttendanceIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new ChangeAttendanceIqProvider();
  }

}
