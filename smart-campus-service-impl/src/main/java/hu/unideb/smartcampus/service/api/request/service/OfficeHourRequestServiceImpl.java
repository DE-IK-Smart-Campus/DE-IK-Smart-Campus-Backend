package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.OfficeHourResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Service for retrivie...
 *
 */
@Service(OfficeHourRequestServiceImpl.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED)
public class OfficeHourRequestServiceImpl
    implements MessageProcessingClass<OfficeHourResponseWrapper> {

  private static final String OK = "OK";

  public static final String BEAN_NAME = "officeHourRequestServiceImpl";

  /**
   * Consulting hour service.
   */
  @Autowired
  private ConsultingHourService consultingHourService;

  /**
   * {@inheritDoc}.
   */
  @Override
  public OfficeHourResponseWrapper getResponse(Object object) {
    CreateOfficeHoursRequest msg = (CreateOfficeHoursRequest) object;
    Integer generateOfficeHoursForInstructor =
        consultingHourService.generateOfficeHoursForInstructor(msg.getInstructorId(),
            msg.getOfficeHours(), msg.getIntervall());
    return OfficeHourResponseWrapper.builder()
        .messageType(RequestMessagesConstants.CREATE_CONSULTING_DATES_RESPONSE).status(OK)
        .numberOfGeneratedHours(generateOfficeHoursForInstructor).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return CreateOfficeHoursRequest.class;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getBeanName() {
    return BEAN_NAME;
  }



}
