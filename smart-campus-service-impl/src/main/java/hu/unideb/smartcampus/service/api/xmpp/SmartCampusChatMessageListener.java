package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.MessageProcessingService;
import hu.unideb.smartcampus.shared.exception.ProcessMessageException;

/**
 * Smart campus chat message listener for default user.
 *
 */
@Component
public class SmartCampusChatMessageListener implements ChatMessageListener {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusChatMessageListener.class);

  @Autowired
  private MessageProcessingService messageProcessService;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void processMessage(Chat chat, Message message) {
    String body = message.getBody();
    if (body != null) {
      String processMessage = null;
      try {
        processMessage = messageProcessService.processMessage(body);
      } catch (ProcessMessageException e) {
        LOGGER.error("Error while processing incoming message.", e);
      }
      if (processMessage != null) {
        try {
          chat.sendMessage(processMessage);
        } catch (NotConnectedException e) {
          LOGGER.error("Error on sending response to client.", e);
        }
      }
    }
  }

}
