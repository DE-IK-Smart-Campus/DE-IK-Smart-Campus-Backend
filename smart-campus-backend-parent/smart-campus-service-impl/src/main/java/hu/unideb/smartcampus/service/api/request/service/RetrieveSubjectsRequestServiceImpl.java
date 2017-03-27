package hu.unideb.smartcampus.service.api.request.service;

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
    LOGGER.info("Retrieving user ({}) subjects.", userId);
    Set<SubjectDetailsEntity> subjects = userRepositoy.getSubjectsByUsername(userId);
    List<SubjectWrapper> subjectsWrapper = createSubjectsWrapper(subjects);
    return subjectsWrapper;
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
        result.add(SubjectWrapper.builder().name(subjectEntity.getSubjectName())
            .instructors(instructors).build());
      });
    }
    return result;
  }

  private InstructorWrapper toInstructorWrapper(InstructorEntity entity) {
    return InstructorWrapper.builder().name(entity.getName()).instructorId(entity.getId()).build();
  }



}
