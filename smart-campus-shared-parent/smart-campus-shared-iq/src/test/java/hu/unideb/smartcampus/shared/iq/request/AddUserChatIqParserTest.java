package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.AddUserChatIqProvider;

/**
 * Add user chat IQ parser test.
 */
public class AddUserChatIqParserTest extends AbstractParserTest {

  private static final String STUDENT = "Emily Stock";
  private static final String CHAT = "smartcampus";

  @Test
  public void testIqProvider() throws Exception {
    AddUserChatIqRequest iq =
        AddUserChatIqRequest.builder().student(STUDENT).chat(CHAT).build();
    AddUserChatIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(CHAT, parse.getChat());
  }

  @Override
  public String getElement() {
    return AddUserChatIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new AddUserChatIqProvider();
  }

}
