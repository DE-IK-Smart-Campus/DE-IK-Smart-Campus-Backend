package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Service for retrivie...
 *
 */
@Service(OfficeHourRequestServiceImpl.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED)
public class OfficeHourRequestServiceImpl implements MessageProcessingClass<BaseWrapper> {

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
  public BaseWrapper getResponse(Object object) {
    CreateOfficeHoursRequest msg = (CreateOfficeHoursRequest) object;
    consultingHourService.generateOfficeHoursForInstructor(msg.getInstructorId(),
        msg.getOfficeHours(), msg.getIntervall());
    return new BaseWrapper(RequestMessagesConstants.CREATE_CONSULTING_DATES_RESPONSE);
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
