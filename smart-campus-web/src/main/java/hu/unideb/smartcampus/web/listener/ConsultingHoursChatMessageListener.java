package hu.unideb.smartcampus.web.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectRetrievalResponseWrapper;

public class ConsultingHoursChatMessageListener implements ChatMessageListener {

  private SubjectRetrievalResponseWrapper result;

  public ConsultingHoursChatMessageListener(SubjectRetrievalResponseWrapper result) {
    this.result = result;
  }

  /**
   * TODO.
   */
  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void processMessage(Chat chat, Message message) {
    String body = message.getBody();
    if (body != null) {
      try {
        result = objectMapper.readValue(body, SubjectRetrievalResponseWrapper.class);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
