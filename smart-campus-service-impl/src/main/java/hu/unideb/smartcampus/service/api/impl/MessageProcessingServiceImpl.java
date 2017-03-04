package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.MessageProcessingService;
import hu.unideb.smartcampus.service.api.context.MessageProcessContext;
import hu.unideb.smartcampus.shared.exception.ProcessMessageException;
import hu.unideb.smartcampus.shared.message.BaseMessageType;

/**
 * Implementation of the message processing service.
 *
 */
@Service
public class MessageProcessingServiceImpl implements MessageProcessingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessingServiceImpl.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MessageProcessContext context;

  @Autowired
  private ApplicationContext appContext;

  /**
   * {@inheritDoc}.
   */
  @Override
  public String processMessage(String message) throws ProcessMessageException {
    BaseMessageType decodedMessage;
    decodedMessage = readDecodedMessage(message);
    LOGGER.info("Decoded JSON:{}", decodedMessage);
    return getResult(decodedMessage);
  }

  private String getResult(BaseMessageType decodedMessage) {
    MessageProcessingClass bean;
    String result = "";
    if (decodedMessage != null) {
      Class<? extends MessageProcessingClass> wb =
          context.getWebServiceMethods().get(decodedMessage.getClass());
      bean = appContext.getBean(wb);
      result = bean.getJson(decodedMessage);
    }
    return result;
  }

  private BaseMessageType readDecodedMessage(String message) throws ProcessMessageException {
    try {
      return objectMapper.readValue(message, BaseMessageType.class);
    } catch (IOException e) {
      LOGGER.error("Error while trying to read message as JSON.", e);
      throw new ProcessMessageException("Error while trying to read message as JSON.", e);
    }
  }

}
