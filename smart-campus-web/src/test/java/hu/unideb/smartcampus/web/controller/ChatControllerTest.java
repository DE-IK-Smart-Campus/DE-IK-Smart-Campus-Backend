package hu.unideb.smartcampus.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.dashboard.ChatController;

@RunWith(MockitoJUnitRunner.class)
public class ChatControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD_CHAT = "/dashboard/chat";
  private static final String VIEW_NAME_DASHBOARD_CHAT = "dashboard/chat";

  @InjectMocks
  private ChatController chatController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.chatController};
  }

  @Test
  public void loadChatViewShouldLoadChatView() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_CHAT))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_CHAT))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_CHAT + VIEW_SUFFIX));
  }
}
