package hu.unideb.smartcampus.service.api.request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.ConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.UserConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SignUpForConsultingHourWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Processing signing up for consulting hours.
 *
 */
@Component(SignUpForConsultingHourRequestServiceImpl.BEAN_NAME)
@Transactional
public class SignUpForConsultingHourRequestServiceImpl
    implements MessageProcessingClass<SignUpForConsultingHourWrapper> {

  public static final String BEAN_NAME = "signUpForConsultingHourRequestServiceImpl";

  private static final String NO_CONSULTING_DATE_EXISTS = "No consulting date exists.";

  private static final String OK = "OK";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ConsultingDateRepository consultingDateRepository;

  @Autowired
  private UserConsultingDateRepository userConsultingDateRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  public SignUpForConsultingHourWrapper getResponse(Object object) {
    SignUpForConsultingHourRequest msg = (SignUpForConsultingHourRequest) object;
    ConsultingDateEntity dateEntity = consultingDateRepository.findOne(msg.getConsultingHourId());
    String status = NO_CONSULTING_DATE_EXISTS;
    if (dateEntity != null) {
      incrementSum(dateEntity);
      consultingDateRepository.save(dateEntity);
      userConsultingDateRepository.save(createUserConsultingDateEntityByRequest(msg, dateEntity));
      status = OK;
    }
    return SignUpForConsultingHourWrapper.builder()
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_RESPONSE).status(status)
        .build();
  }

  private UserConsultingDateEntity createUserConsultingDateEntityByRequest(
      SignUpForConsultingHourRequest msg, ConsultingDateEntity dateEntity) {
    UserEntity userEntity = userRepository.findOne(msg.getUserId());
    return UserConsultingDateEntity.builder().user(userEntity).consultingDate(dateEntity)
        .reason(msg.getReason()).duration(msg.getDuration()).build();
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

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getBeanName() {
    return BEAN_NAME;
  }

}
