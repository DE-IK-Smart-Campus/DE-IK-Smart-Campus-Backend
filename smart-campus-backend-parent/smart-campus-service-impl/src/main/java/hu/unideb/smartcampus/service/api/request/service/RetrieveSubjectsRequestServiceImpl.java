package hu.unideb.smartcampus.service.api.request.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.shared.wrapper.inner.InstructorWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.SubjectWrapper;

/**
 * Service for retrieve the given user's subjects.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RetrieveSubjectsRequestServiceImpl implements RetrieveSubjectsRequestService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(RetrieveSubjectsRequestServiceImpl.class);

  @Autowired
  private UserRepository userRepositoy;

  @Autowired
  private InstructorRepository instructorRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<SubjectWrapper> getSubjects(String userId) {
    LocalDate from = getFrom();
    LocalDate to = getTo();
    LOGGER.info("Retrieving user ({}) subjects between {} and {}.", userId, from, to);
    Set<SubjectDetailsEntity> subjects =
        userRepositoy.getSubjectsWithinRangeByUsername(userId, from, to);
    return createSubjectsWrapper(subjects);
  }

  private LocalDate getTo() {
    LocalDate now = LocalDate.now();
    LocalDate result;
    if (isFirstSemester(now)) {
      result = LocalDate.now().withMonth(5).withDayOfMonth(31);
    } else {
      result = LocalDate.now().withMonth(12).withDayOfMonth(31);
    }
    return result;
  }

  private boolean isFirstSemester(LocalDate now) {
    return now.compareTo(LocalDate.now().withMonth(1).withDayOfMonth(1))
        * now.compareTo(LocalDate.now().withMonth(5).withDayOfMonth(1)) <= 0;
  }

  private LocalDate getFrom() {
    LocalDate now = LocalDate.now();
    LocalDate result;
    if (isFirstSemester(now)) {
      result = LocalDate.now().withMonth(1).withDayOfMonth(1);
    } else {
      result = LocalDate.now().withMonth(9).withDayOfMonth(1);
    }
    return result;
  }

  private List<InstructorWrapper> convertEntitiesToWrapper(Set<InstructorEntity> instructorSet) {
    return instructorSet.stream().map(this::toInstructorWrapper).collect(Collectors.toList());
  }

  private List<SubjectWrapper> createSubjectsWrapper(Set<SubjectDetailsEntity> subjects) {
    List<SubjectWrapper> result = new ArrayList<>();
    if (subjects != null) {
      subjects.stream().forEach(subjectEntity -> {
        Set<InstructorEntity> instructorSet =
            instructorRepository.getInstructorsBySubjectName(subjectEntity.getSubjectName());
        List<InstructorWrapper> instructors = convertEntitiesToWrapper(instructorSet);
        result.add(SubjectWrapper.builder()
            .name(subjectEntity.getSubjectName())
            .instructors(instructors).build());
      });
    }
    return result.stream().distinct().collect(Collectors.toList());
  }

  private InstructorWrapper toInstructorWrapper(InstructorEntity entity) {
    return InstructorWrapper.builder().name(entity.getName()).instructorId(entity.getId()).build();
  }



}
