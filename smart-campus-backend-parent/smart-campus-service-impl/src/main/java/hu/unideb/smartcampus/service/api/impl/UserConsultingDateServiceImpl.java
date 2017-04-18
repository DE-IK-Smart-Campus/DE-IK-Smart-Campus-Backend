package hu.unideb.smartcampus.service.api.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional(readOnly = true)
  @Override
  public List<StudentIqElement> findSignedStudentByInstructorIdWithinOneWeek(String neptunIdentifier) {
    LOGGER.info("Find user consulting dates for instructor {} within a week.", neptunIdentifier);
    LocalDateTime from = LocalDateTime.now();
    LocalDateTime to = LocalDateTime.now().plusWeeks(1);
    List<UserConsultingDateEntity> userConsultingDates =
        userConsultingDateRepository.getUserConsultingDatesByInstructorIdBetweenRange(neptunIdentifier,
            from, to);
    LOGGER.debug("Found dates:{}.", userConsultingDates.size());
    return toList(userConsultingDates);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<StudentIqElement> listSignedStudentByInstructorId(String neptunIdentifier) {
    LOGGER.info("Find all user consulting dates for instructor {}.", neptunIdentifier);
    List<UserConsultingDateEntity> userConsultingDates =
        userConsultingDateRepository.getUserConsultingDatesByInstructorId(neptunIdentifier);
    LOGGER.debug("Found dates:{}.", userConsultingDates.size());
    return toList(userConsultingDates);
  }


  private List<StudentIqElement> toList(List<UserConsultingDateEntity> userConsultingDates) {
    return userConsultingDates.stream().map(converter::convert).collect(Collectors.toList());
  }

}
