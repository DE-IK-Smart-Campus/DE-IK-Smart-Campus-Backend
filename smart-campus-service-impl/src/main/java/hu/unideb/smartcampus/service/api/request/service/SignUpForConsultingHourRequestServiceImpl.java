package hu.unideb.smartcampus.service.api.request.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
@Service(SignUpForConsultingHourRequestServiceImpl.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED)
public class SignUpForConsultingHourRequestServiceImpl
    implements MessageProcessingClass<SignUpForConsultingHourWrapper> {

  public static final String BEAN_NAME = "signUpForConsultingHourRequestServiceImpl";

  private static final String USER_DOES_NOT_EXISTS = "User does not exists.";

  private static final String NO_CONSULTING_DATE_EXISTS = "No consulting date exists.";

  private static final String OK = "OK";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SignUpForConsultingHourRequestServiceImpl.class);

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
    UserEntity userEntity;
    String status = NO_CONSULTING_DATE_EXISTS;
    if (dateEntity != null) {
      status = USER_DOES_NOT_EXISTS;
      userEntity = userRepository.findByUsername(msg.getUserId());
      if (userEntity != null) {
        incrementAndSaveConsultingSignUp(msg, dateEntity, userEntity);
        status = OK;
      }
    }
    return SignUpForConsultingHourWrapper.builder()
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_RESPONSE).status(status)
        .build();
  }

  private void incrementAndSaveConsultingSignUp(SignUpForConsultingHourRequest msg,
      ConsultingDateEntity dateEntity, UserEntity userEntity) {
    LOGGER.info("Signing up user ({}) for consulting hour ({}).", msg.getUserId(),
        dateEntity.getDateInString());
    incrementSum(dateEntity);
    consultingDateRepository.save(dateEntity);
    userConsultingDateRepository
        .save(createUserConsultingDateEntityByRequest(msg, dateEntity, userEntity));
  }

  private UserConsultingDateEntity createUserConsultingDateEntityByRequest(
      SignUpForConsultingHourRequest msg, ConsultingDateEntity dateEntity, UserEntity userEntity) {
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
