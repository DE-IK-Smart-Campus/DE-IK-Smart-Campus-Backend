package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Smart campus chat manager listener for default user.
 *
 */
@Component
public class SmartCampusChatManagerListener implements ChatManagerListener {

  @Autowired
  private ChatMessageListener messageListener;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void chatCreated(Chat chat, boolean createdLocally) {
    chat.addMessageListener(messageListener);
  }

}
