package hu.unideb.smartcampus.service.api.messageprocess;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.Subject;
import hu.unideb.smartcampus.shared.message.AskSubjectsProcessingMessage;
import hu.unideb.smartcampus.shared.message.BaseMessageType;

/**
 * Service for retrivie the given user's subjects.
 *
 */
@Component
public class AskSubjectsProcessingMessageServiceImpl implements MessageProcessingClass {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AskSubjectsProcessingMessageServiceImpl.class);

  @Autowired
  private ConsultingHourService service;

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getJson(Object object) {
    LOGGER.info("Retrieving user's subjects.");
    AskSubjectsProcessingMessage msg = (AskSubjectsProcessingMessage) object;
    Set<Subject> subjects = service.getSubjectsByUserId(msg.getUserId());
    try {
      return objectMapper.writeValueAsString(subjects);
    } catch (JsonProcessingException e) {
      LOGGER.error("Error while creating JSON.");
    }
    return "";
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseMessageType> getSupportedClass() {
    return AskSubjectsProcessingMessage.class;
  }



}
