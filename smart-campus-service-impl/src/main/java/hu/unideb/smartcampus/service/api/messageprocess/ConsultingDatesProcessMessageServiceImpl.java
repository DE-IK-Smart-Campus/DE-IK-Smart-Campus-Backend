package hu.unideb.smartcampus.service.api.messageprocess;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.message.BaseMessageType;
import hu.unideb.smartcampus.shared.message.ConsultingDatesProcessMessage;

/**
 * Service for retrivie...
 *
 */
@Component
public class ConsultingDatesProcessMessageServiceImpl implements MessageProcessingClass {

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getJson(Object object) {
    return "Consulting dates....";
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseMessageType> getSupportedClass() {
    return ConsultingDatesProcessMessage.class;
  }



}
