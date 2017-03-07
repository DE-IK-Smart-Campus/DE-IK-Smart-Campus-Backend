package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.repository.ConsultingDateRepository;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SignUpForConsultingHourWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Processing signing up for consulting hours.
 *
 */
@Component
public class SignUpForConsultingHourRequestServiceImpl
    implements MessageProcessingClass<SignUpForConsultingHourWrapper> {


  private static final String OK = "OK";
  
  @Autowired
  private ConsultingDateRepository consultingDateRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  public SignUpForConsultingHourWrapper getResponse(Object object) {
    SignUpForConsultingHourRequest msg = (SignUpForConsultingHourRequest) object;
    ConsultingDateEntity dateEntity = consultingDateRepository.findOne(msg.getConsultingHourId());
    if (dateEntity != null) {
      incrementSum(dateEntity);
      consultingDateRepository.save(dateEntity);
    }
    return SignUpForConsultingHourWrapper.builder()
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_RESPONSE).status(OK)
        .build();

  }

  private void incrementSum(ConsultingDateEntity dateEntity) {
    Integer sum = dateEntity.getSum();
    sum = sum == null ? 1 : sum + 1;
    dateEntity.setSum(sum);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return SignUpForConsultingHourRequest.class;
  }

}
