package hu.unideb.smartcampus.service.api.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.persistence.repository.UserConsultingDateRepository;
import hu.unideb.smartcampus.service.api.UserConsultingDateService;
import hu.unideb.smartcampus.service.api.converter.toiq.UserConsultingDateEntityToStudentIqElementConverter;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * User consulting date service implementation.
 */
@Service
public class UserConsultingDateServiceImpl implements UserConsultingDateService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserConsultingDateServiceImpl.class);

  @Autowired
  private UserConsultingDateRepository userConsultingDateRepository;

  @Autowired
  private UserConsultingDateEntityToStudentIqElementConverter converter;

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<StudentIqElement> findSignedStudentByInstructorIdWithinOneWeek(Long instructorId) {
    LOGGER.info("Find user consulting dates for instructor {} within a week.", instructorId);
    Timestamp from = Timestamp.valueOf(LocalDateTime.now());
    Timestamp to = Timestamp.valueOf(LocalDateTime.now().plusWeeks(1));
    List<UserConsultingDateEntity> userConsultingDates =
        userConsultingDateRepository.getUserConsultingDatesByInstructorIdBetweenRange(instructorId,
            from, to);
    LOGGER.debug("Found dates:{}.", userConsultingDates.size());
    return toList(userConsultingDates);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<StudentIqElement> listSignedStudentByInstructorId(Long instructorId) {
    LOGGER.info("Find all user consulting dates for instructor {}.", instructorId);
    List<UserConsultingDateEntity> userConsultingDates =
        userConsultingDateRepository.getUserConsultingDatesByInstructorId(instructorId);
    LOGGER.debug("Found dates:{}.", userConsultingDates.size());
    return toList(userConsultingDates);
  }


  private List<StudentIqElement> toList(List<UserConsultingDateEntity> userConsultingDates) {
    return userConsultingDates.stream().map(converter::convert).collect(Collectors.toList());
  }

}
