package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.MessageProcessingService;
import hu.unideb.smartcampus.service.api.context.MessageProcessContext;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.shared.exception.ProcessMessageException;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;

/**
 * Implementation of the message processing service.
 *
 */
@Service
public class MessageProcessingServiceImpl implements MessageProcessingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessingServiceImpl.class);

  private ObjectMapper objectMapper;

  private MessageProcessContext context;

  private ApplicationContext appContext;

  /**
   * Constructs a MessageProcessingServiceImpl instance with injected dependencies.
   *
   */
  public MessageProcessingServiceImpl(ObjectMapper objectMapper, MessageProcessContext context,
      ApplicationContext appContext) {
    this.objectMapper = objectMapper;
    this.context = context;
    this.appContext = appContext;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public <T extends BaseWrapper> T processMessage(String message) throws ProcessMessageException {
    BaseRequestType decodedMessage;
    decodedMessage = readDecodedMessage(message);
    LOGGER.info("Decoded JSON:{}", decodedMessage);
    return getResult(decodedMessage);
  }

  private <T extends BaseWrapper> T getResult(BaseRequestType decodedMessage) {
    MessageProcessingClass bean;
    BaseWrapper result = null;
    if (decodedMessage != null) {
      bean = getProcessBean(decodedMessage);
      result = bean.getResponse(decodedMessage);
    }
    return (T) result;
  }

  private MessageProcessingClass getProcessBean(BaseRequestType decodedMessage) {
    Class<? extends MessageProcessingClass> wb =
        context.getMessageServices().get(decodedMessage.getClass());
    return appContext.getBean(wb);
  }

  private BaseRequestType readDecodedMessage(String message) throws ProcessMessageException {
    try {
      return objectMapper.readValue(message, BaseRequestType.class);
    } catch (IOException e) {
      throw new ProcessMessageException("Error while reading message, unknown message type.", e);
    }
  }

}
