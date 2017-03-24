package hu.unideb.smartcampus.service.api.xmpp;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.service.api.MessageProcessingService;
import hu.unideb.smartcampus.shared.exception.ProcessMessageException;
import hu.unideb.smartcampus.shared.wrapper.BaseWrapper;
import hu.unideb.smartcampus.xmpp.exception.ChatResponseException;

/**
 * Smart campus chat message listener for default user.
 *
 */
@Component
public class SmartCampusChatMessageListener implements IncomingChatMessageListener {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SmartCampusChatMessageListener.class);

  @Autowired
  private MessageProcessingService messageProcessService;

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
    String body = message.getBody();
    if (body != null) {
      BaseWrapper processMessage = null;
      try {
        processMessage = messageProcessService.processMessage(body);
      } catch (ProcessMessageException e) {
        LOGGER.error("Error:", e);
        sendExceptionAsMessage(chat, e);
      }
      if (processMessage != null) {
        sendObject(chat, processMessage);
      }
    }
  }

  private void sendObject(Chat chat, Object o) {
    try {
      sendMessage(chat, objectMapper.writeValueAsString(o));
    } catch (JsonProcessingException e) {
      LOGGER.error("Error on writing JSON.", e);
    }
  }

  private void sendExceptionAsMessage(Chat chat, ProcessMessageException e) {
    sendObject(chat, createProcessingException(e));
  }

  private ChatResponseException createProcessingException(ProcessMessageException e) {
    return ChatResponseException.builder().message(e.getCause().getMessage())
        .exceptionType(e.getClass().toString()).build();
  }

  private void sendMessage(Chat chat, String msg) {
    try {
      chat.send(msg);
    } catch (NotConnectedException | InterruptedException e) {
      LOGGER.error("Error on sending response to client.", e);
    }
  }


}