package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.UserMucListIqProvider;

/**
 * MUC listing parser test.
 */
public class UserMucListIqParserTest extends AbstractParserTest {

  private static final String STUDENT = "Emily Stock";
  private static final List<String> MUC_CHAT_LIST = Arrays.asList("Room1", "Room2");

  @Test
  public void testIqProvider() throws Exception {
    UserMucListIqRequest iq =
        UserMucListIqRequest.builder().student(STUDENT).mucChatList(MUC_CHAT_LIST).build();
    UserMucListIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(MUC_CHAT_LIST, parse.getMucChatList());
  }

  @Override
  public String getElement() {
    return UserMucListIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new UserMucListIqProvider();
  }

}
