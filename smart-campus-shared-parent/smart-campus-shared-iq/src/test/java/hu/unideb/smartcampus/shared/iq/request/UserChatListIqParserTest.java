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
  private static final List<String> MUC_CHAT_LIST = Arrays.asList("Room1", "Room2");

  @Test
  public void testIqProvider() throws Exception {
    ListUserChatsIqRequest iq =
        ListUserChatsIqRequest.builder()
        .student(STUDENT)
        .chatList(CHAT_LIST)
        .mucChatList(MUC_CHAT_LIST)
        .build();
    ListUserChatsIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(CHAT_LIST, parse.getChatList());
    Assert.assertEquals(MUC_CHAT_LIST,parse.getMucChatList());
  }

  @Override
  public String getElement() {
    return ListUserChatsIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new UserChatListIqProvider();
  }

}
