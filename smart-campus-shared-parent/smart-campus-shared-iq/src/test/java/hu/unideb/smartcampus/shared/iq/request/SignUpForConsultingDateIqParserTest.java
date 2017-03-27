package hu.unideb.smartcampus.shared.iq.request;

import java.io.CharArrayReader;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlpull.mxp1.MXParser;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.provider.SignUpForConsultingHourIqProvider;

/**
 * SignUpForConsultingDate IQ parser test.
 */
public class SignUpForConsultingDateIqParserTest {

  private static final Long CONSULTING_HOUR_ID = 2L;
  private static final String USER_ID = "1";
  private static final String REASON = "Why not?";
  private static final String DURATION = "A moment.";

  @Test
  @Ignore
  public void testProvider() throws Exception {
    SignUpForConsultingDateIqRequest iq = SignUpForConsultingDateIqRequest.builder().userId(USER_ID)
        .consultingHourId(CONSULTING_HOUR_ID).duration(DURATION).reason(REASON).build();
    SignUpForConsultingHourIqProvider provider = new SignUpForConsultingHourIqProvider();
    XmlPullParser parser = new MXParser();
    Reader in = new CharArrayReader(iq.toXml().toCharArray());
    parser.setInput(in);
    SignUpForConsultingDateIqRequest parse = provider.parse(parser, 0);
    Assert.assertEquals(USER_ID, parse.getUserId());
    Assert.assertEquals(CONSULTING_HOUR_ID, parse.getConsultingHourId());
    Assert.assertEquals(DURATION, parse.getDuration());
    Assert.assertEquals(REASON, parse.getReason());
  }


}
