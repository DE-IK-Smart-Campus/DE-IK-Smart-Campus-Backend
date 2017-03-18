package hu.unideb.smartcampus.web.listener;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;

import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectRetrievalResponseWrapper;

public class ConsultingHoursChatManagerListener implements ChatManagerListener {

  private SubjectRetrievalResponseWrapper result;

  public ConsultingHoursChatManagerListener(SubjectRetrievalResponseWrapper result) {
    this.result = result;
  }

  @Override
  public void chatCreated(Chat chat, boolean b) {
    chat.addMessageListener(new ConsultingHoursChatMessageListener(result));
  }
}
