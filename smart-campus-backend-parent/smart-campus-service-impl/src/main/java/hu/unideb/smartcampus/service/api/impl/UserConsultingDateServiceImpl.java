package hu.unideb.smartcampus.service.api.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.persistence.repository.UserConsultingDateRepository;
import hu.unideb.smartcampus.service.api.UserConsultingDateService;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * User consulting date service implementation.
 */
@Service
public class UserConsultingDateServiceImpl implements UserConsultingDateService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserConsultingDateServiceImpl.class);

  @Autowired
  private UserConsultingDateRepository userConsultingDateRepository;

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<InstructorConsultingDateIqElement> findSignedStudentByInstructorIdWithinOneWeek(
      String neptunIdentifier) {
    LOGGER.info("Find user consulting dates for instructor {} within a week.", neptunIdentifier);
    LocalDateTime from = LocalDateTime.now();
    LocalDateTime to = LocalDateTime.now().plusWeeks(1);
    List<UserConsultingDateEntity> userConsultingDates =
        userConsultingDateRepository.getUserConsultingDatesByInstructorIdBetweenRange(
            neptunIdentifier,
            from, to);
    List<ConsultingDateEntity> consultingDates = userConsultingDates.stream()
        .map(consultingDate -> consultingDate.getConsultingDate()).collect(Collectors.toList());

    LOGGER.debug("Found dates:{}.", userConsultingDates.size());
    return create(consultingDates, userConsultingDates);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<InstructorConsultingDateIqElement> listSignedStudentByInstructorId(
      String neptunIdentifier) {
    LOGGER.info("Find all user consulting dates for instructor {}.", neptunIdentifier);
    List<UserConsultingDateEntity> userConsultingDates =
        userConsultingDateRepository.getUserConsultingDatesByInstructorId(neptunIdentifier);
    List<ConsultingDateEntity> consultingDates = userConsultingDates.stream()
        .map(consultingDate -> consultingDate.getConsultingDate()).collect(Collectors.toList());
    LOGGER.debug("Found dates:{}.", userConsultingDates.size());
    return create(consultingDates, userConsultingDates);
  }


  private List<InstructorConsultingDateIqElement> create(List<ConsultingDateEntity> consultingDates,
      List<UserConsultingDateEntity> userConsultingDates) {
    List<InstructorConsultingDateIqElement> result = new ArrayList<>();
    for (ConsultingDateEntity consultingDate : consultingDates) {
      List<UserConsultingDateEntity> collect = userConsultingDates.stream()
          .filter(p -> p.getConsultingDate().equals(consultingDate)).collect(Collectors.toList());
      List<StudentIqElement> students =
          collect.stream().map(this::toStudentIqElement).collect(Collectors.toList());
      result.add(InstructorConsultingDateIqElement.builder()
          .consultingDateId(consultingDate.getId())
          .day(consultingDate.getDateInString())
          .students(students)
          .build());
    }
    return result;
  }

  private StudentIqElement toStudentIqElement(UserConsultingDateEntity entity) {
    return StudentIqElement.builder()
        .studentName(entity.getUser().getFullName())
        .neptunIdentifier(entity.getUser().getNeptunIdentifier())
        .reason(entity.getReason())
        .duration(entity.getDuration())
        .build();
  }

}
