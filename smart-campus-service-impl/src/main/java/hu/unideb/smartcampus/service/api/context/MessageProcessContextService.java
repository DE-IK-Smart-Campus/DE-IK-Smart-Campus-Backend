package hu.unideb.smartcampus.service.api.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.message.BaseMessageType;

/**
 * Base impl.
 *
 */
@Service
public class MessageProcessContextService implements MessageProcessContext {


  /**
   * logger.
   */
  private static final Logger LOGGER =
      LoggerFactory.getLogger(MessageProcessContextService.class);

  /**
   * map.
   */
  private Map<Class<? extends BaseMessageType>, Class<? extends MessageProcessingClass>> webServiceMethods;

  /**
   * classes.
   */
  @Autowired
  private List<MessageProcessingClass> classes;

  /**
   * Base method impl.
   */
  public Map<Class<? extends BaseMessageType>, Class<? extends MessageProcessingClass>> getWebServiceMethods() {
    if (webServiceMethods == null) {
      webServiceMethods =
          new HashMap<Class<? extends BaseMessageType>, Class<? extends MessageProcessingClass>>();
      for (Object clazz : classes) {
        webServiceMethods.put(((MessageProcessingClass) clazz).getSupportedClass(),
            ((MessageProcessingClass) clazz).getClass());
      }
    }
    LOGGER.info("MessageService:{}", webServiceMethods);
    return webServiceMethods;
  }

}
