package hu.unideb.smartcampus.service.api.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;

/**
 * Implementation of {@link MessageProcessContext} which will be responsible for pairing the
 * message/request type to services.
 *
 */
@Service
public class MessageProcessContextImpl implements MessageProcessContext {

  /**
   * Class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessContextImpl.class);

  /**
   * Map of request process services.
   */
  private Map<Class<? extends BaseRequestType>, Class<? extends MessageProcessingClass>> requestProcessServices;

  /**
   * List of message process services.
   */
  private List<MessageProcessingClass> classes;

  /**
   * Constructs the context with classes.
   *
   */
  @Autowired
  public MessageProcessContextImpl(List<MessageProcessingClass> classes) {
    this.classes = classes;
  }

  /**
   * {@inheritDoc}.
   */
  public Map<Class<? extends BaseRequestType>, Class<? extends MessageProcessingClass>> getMessageServices() {
    if (requestProcessServices == null) {
      requestProcessServices =
          new HashMap<Class<? extends BaseRequestType>, Class<? extends MessageProcessingClass>>();
      for (Object clazz : classes) {
        requestProcessServices.put(((MessageProcessingClass) clazz).getSupportedClass(),
            ((MessageProcessingClass) clazz).getClass());
      }
    }
    LOGGER.info("MessageService:{}", requestProcessServices);
    return requestProcessServices;
  }

}
