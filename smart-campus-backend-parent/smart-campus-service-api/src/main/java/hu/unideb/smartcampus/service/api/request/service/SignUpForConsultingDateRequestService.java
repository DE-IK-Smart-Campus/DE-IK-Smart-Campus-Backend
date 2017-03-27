package hu.unideb.smartcampus.service.api.request.service;

import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.wrapper.SignUpForConsultingHourWrapper;

/**
 * Service for sign up for consulting date.
 */
public interface SignUpForConsultingDateRequestService {

  /**
   * Sign up student for consulting hour by request.
   */
  SignUpForConsultingHourWrapper signUpByRequest(SignUpForConsultingHourRequest request);

}
