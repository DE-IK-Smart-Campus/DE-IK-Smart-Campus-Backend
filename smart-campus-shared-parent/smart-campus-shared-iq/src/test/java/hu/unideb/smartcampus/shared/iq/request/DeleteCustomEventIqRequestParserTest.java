package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.DeleteCustomEventIqProvider;

/**
 * Calendar subjects IQ parser test.
 */
public class DeleteCustomEventIqRequestParserTest extends AbstractParserTest {

  private static final Long EVENT_ID = 10L;

  private static final String STUDENT = "testUser";

  @Test
  public void testIqProvider() throws Exception {
    DeleteCustomEventIqRequest iq =
        DeleteCustomEventIqRequest.builder()
            .student(STUDENT)
            .eventId(EVENT_ID)
            .build();
    DeleteCustomEventIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(EVENT_ID, parse.getEventId());
  }

  @Override
  public String getElement() {
    return DeleteCustomEventIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new DeleteCustomEventIqProvider();
  }

}
