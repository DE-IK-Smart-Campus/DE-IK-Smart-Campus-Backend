package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.OfficeHourResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Service for retrivie...
 *
 */
@Service(OfficeHourCreationRequestServiceImpl.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED)
public class OfficeHourCreationRequestServiceImpl implements OfficeHourCreationRequestService {

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
  public OfficeHourResponseWrapper createOfficeHourByRequest(CreateOfficeHoursRequest request) {
    Integer generateOfficeHoursForInstructor =
        consultingHourService.generateOfficeHoursForInstructor(request.getInstructorId(),
            request.getOfficeHours(), request.getIntervall());
    return OfficeHourResponseWrapper.builder()
        .messageType(RequestMessagesConstants.CREATE_CONSULTING_DATES_RESPONSE).status(OK)
        .numberOfGeneratedHours(generateOfficeHoursForInstructor).build();
  }
}
