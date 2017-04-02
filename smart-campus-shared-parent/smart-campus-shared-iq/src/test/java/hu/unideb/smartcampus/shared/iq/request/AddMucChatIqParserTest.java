package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.AddMucChatIqProvider;

/**
 * Add MUC IQ parser test.
 */
public class AddMucChatIqParserTest extends AbstractParserTest {

  private static final String STUDENT = "Emily Stock";
  private static final String MUC = "Room1";

  @Test
  public void testIqProvider() throws Exception {
    AddMucChatIqRequest iq =
        AddMucChatIqRequest.builder().student(STUDENT).muc(MUC).build();
    AddMucChatIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(MUC, parse.getMuc());
  }

  @Override
  public String getElement() {
    return AddMucChatIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new AddMucChatIqProvider();
  }

}
