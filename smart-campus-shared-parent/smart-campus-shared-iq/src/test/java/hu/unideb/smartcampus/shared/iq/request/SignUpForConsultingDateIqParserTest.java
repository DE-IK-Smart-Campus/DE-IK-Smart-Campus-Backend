package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.SignUpForConsultingHourIqProvider;

/**
 * SignUpForConsultingDate IQ parser test.
 */
public class SignUpForConsultingDateIqParserTest extends AbstractParserTest {

  private static final Long CONSULTING_HOUR_ID = 2L;
  private static final String USER_ID = "1";
  private static final String REASON = "Why not?";
  private static final String DURATION = "A moment.";

  @Test
  public void testProvider() throws Exception {
    SignUpForConsultingDateIqRequest iq = SignUpForConsultingDateIqRequest.builder().userId(USER_ID)
        .consultingHourId(CONSULTING_HOUR_ID).duration(DURATION).reason(REASON).build();
    SignUpForConsultingDateIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(USER_ID, parse.getUserId());
    Assert.assertEquals(CONSULTING_HOUR_ID, parse.getConsultingHourId());
    Assert.assertEquals(DURATION, parse.getDuration());
    Assert.assertEquals(REASON, parse.getReason());
  }

  @Override
  public String getElement() {
    return SignUpForConsultingDateIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new SignUpForConsultingHourIqProvider();
  }


}
