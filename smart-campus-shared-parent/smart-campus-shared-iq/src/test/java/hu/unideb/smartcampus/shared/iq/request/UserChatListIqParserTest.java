package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.UserChatListIqProvider;

/**
 * MUC listing parser test.
 */
public class UserChatListIqParserTest extends AbstractParserTest {

  private static final String STUDENT = "Emily Stock";
  private static final List<String> CHAT_LIST = Arrays.asList("AndroidMember", "WebMember");

  @Test
  public void testIqProvider() throws Exception {
    UserChatListIqRequest iq =
        UserChatListIqRequest.builder().student(STUDENT).chatList(CHAT_LIST).build();
    UserChatListIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(CHAT_LIST, parse.getChatList());
  }

  @Override
  public String getElement() {
    return UserChatListIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new UserChatListIqProvider();
  }

}
