package hu.unideb.smartcampus.service.api.request.service;

import hu.unideb.smartcampus.service.api.domain.response.wrapper.OfficeHourResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;

/**
 * Service for creating office hours.
 */
public interface OfficeHourCreationRequestService {

  /**
   * Create office hours by request.
   */
  OfficeHourResponseWrapper createOfficeHourByRequest(CreateOfficeHoursRequest request);

}
