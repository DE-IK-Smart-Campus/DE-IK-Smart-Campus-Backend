package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.SaveSubjectsIcsIqProvider;

/**
 * Save subjects IQ provider test.
 */
public class SaveSubjectsIqParserTest extends AbstractParserTest {


  private static final String STATUS_MESSAGE = "OK";
  private static final String STUDENT = "Example Student";
  private static final String ICS = "http://neptun.unideb.hu/xyz";

  @Test
  public void testIqProvider() throws Exception {
    SaveSubjectsIcsIqRequest iq = SaveSubjectsIcsIqRequest.builder().student(STUDENT).ics(ICS)
        .statusMessage(STATUS_MESSAGE).build();
    SaveSubjectsIcsIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(ICS, parse.getIcs());
    Assert.assertEquals(STATUS_MESSAGE, parse.getStatusMessage());
  }

  @Override
  public String getElement() {
    return SaveSubjectsIcsIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new SaveSubjectsIcsIqProvider();
  }

}
