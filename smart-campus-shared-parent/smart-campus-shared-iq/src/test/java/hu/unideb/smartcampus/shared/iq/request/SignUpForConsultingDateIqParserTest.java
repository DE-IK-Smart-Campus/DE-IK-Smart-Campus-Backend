package hu.unideb.smartcampus.shared.iq.request;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * SignUpForConsultingDate IQ parser test.
 */
public class SignUpForConsultingDateIqParserTest extends AbstractIqParserTest {

  private static final Long CONSULTING_HOUR_ID = 2L;
  private static final String USER_ID = "1";
  private static final String REASON = "Why not?";
  private static final String DURATION = "A moment.";
  private static final String FILE = "src/test/resources/signUpForConsultingDate.xml";

  @Before
  public void before() {
    super.before();
  }

  @Test
  public void testParseXml() throws JAXBException {
    SignUpForConsultingDateIqRequest iq = SignUpForConsultingDateIqRequest.builder().userId(USER_ID)
        .consultingHourId(CONSULTING_HOUR_ID).duration(DURATION).reason(REASON).build();
    SignUpForConsultingDateIqRequest result = (SignUpForConsultingDateIqRequest) runTest(iq);
    Assert.assertEquals(USER_ID, result.getUserId());
    Assert.assertEquals(CONSULTING_HOUR_ID, result.getConsultingHourId());
    Assert.assertEquals(REASON, result.getReason());
    Assert.assertEquals(DURATION, result.getDuration());
  }

  @Override
  protected String getFile() {
    return FILE;
  }

}
