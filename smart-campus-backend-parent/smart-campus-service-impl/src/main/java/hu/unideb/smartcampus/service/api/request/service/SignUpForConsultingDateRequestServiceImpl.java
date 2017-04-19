package hu.unideb.smartcampus.service.api.request.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.ConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.persistence.repository.UserConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.shared.iq.request.AddCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import hu.unideb.smartcampus.shared.util.DateUtil;
import hu.unideb.smartcampus.shared.wrapper.SignUpForConsultingHourWrapper;

/**
 * Processing signing up for consulting hours.
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SignUpForConsultingDateRequestServiceImpl
    implements SignUpForConsultingDateRequestService {

  private static final String USER_DOES_NOT_EXISTS = "User does not exists.";

  private static final String NO_CONSULTING_DATE_EXISTS = "No consulting date exists.";

  private static final String OK = "OK";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SignUpForConsultingDateRequestServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ConsultingDateRepository consultingDateRepository;

  @Autowired
  private UserConsultingDateRepository userConsultingDateRepository;

  @Autowired
  private CustomEventService customEventService;

  @Autowired
  private InstructorRepository instructorRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  public SignUpForConsultingHourWrapper signUpByRequest(SignUpForConsultingHourRequest request) {
    ConsultingDateEntity dateEntity =
        consultingDateRepository.findOne(request.getConsultingHourId());
    UserEntity userEntity;
    String status = NO_CONSULTING_DATE_EXISTS;
    if (dateEntity != null) {
      status = USER_DOES_NOT_EXISTS;
      userEntity = userRepository.findByUsername(request.getUserId());
      if (userEntity != null) {
        incrementAndSaveConsultingSignUp(request, dateEntity, userEntity);
        addCustomEvent(request, dateEntity, userEntity);
        status = OK;
      }
    }
    return SignUpForConsultingHourWrapper.builder()
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_RESPONSE).status(status)
        .build();
  }

  private void addCustomEvent(SignUpForConsultingHourRequest request,
      ConsultingDateEntity dateEntity, UserEntity userEntity) {
    InstructorEntity instructorEntity =
        instructorRepository.getInstructorByConsultingDateId(dateEntity.getId());
    customEventService.addCustomEntityByIq(AddCustomEventIqRequest.builder()
        .student(userEntity.getUsername())
        .customEvent(CustomEventIqElement.builder()
            .eventWhen(DateUtil.getInEpochLongByLocalDateTime(dateEntity.getFromToDate()
                .getFromDate()
                .toLocalDate()
                .atStartOfDay()))
            .eventStart(
                DateUtil.getInEpochLongByLocalDateTime(dateEntity.getFromToDate().getFromDate()))
            .eventEnd(
                DateUtil.getInEpochLongByLocalDateTime(dateEntity.getFromToDate().getToDate()))
            .eventName("Meeting - " + instructorEntity.getName())
            .eventDescription(
                "Meeting with " + instructorEntity.getName() + ", " + request.getReason())
            .eventPlace(instructorEntity.getRoom() != null ? instructorEntity.getRoom() : "Szoba")
            .build())
        .build());
  }

  private UserConsultingDateEntity createUserConsultingDateEntityByRequest(
      SignUpForConsultingHourRequest msg, ConsultingDateEntity dateEntity, UserEntity userEntity) {
    return UserConsultingDateEntity.builder().user(userEntity).consultingDate(dateEntity)
        .reason(msg.getReason()).duration(msg.getDuration()).build();
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

  private void incrementSum(ConsultingDateEntity dateEntity) {
    Integer sum = dateEntity.getSum();
    sum = sum == null ? 1 : sum + 1;
    dateEntity.setSum(sum);
  }

}
