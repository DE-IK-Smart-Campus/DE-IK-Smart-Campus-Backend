package hu.unideb.smartcampus.service.api.request.service;

import static hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants.RETRIEVE_SUBJECTS_RESPONSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectRetrievalResponseWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.InstructorWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.SubjectWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveSubjectsRequest;

/**
 * Service for retrieve the given user's subjects.
 *
 */
@Component(RetrieveSubjectsRequestServiceImpl.BEAN_NAME)
public class RetrieveSubjectsRequestServiceImpl
    implements MessageProcessingClass<SubjectRetrievalResponseWrapper> {

  public static final String BEAN_NAME = "retrieveSubjectsRequestServiceImpl";

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
  public SubjectRetrievalResponseWrapper getResponse(Object object) {
    RetrieveSubjectsRequest msg = (RetrieveSubjectsRequest) object;
    LOGGER.info("Retrieving user ({}) subjects.", msg.getUserId());
    Set<SubjectEntity> subjects = userRepositoy.getSubjectsByUserId(msg.getUserId());
    List<SubjectWrapper> subjectsWrapper = createSubjectsWrapper(subjects);

    return SubjectRetrievalResponseWrapper.builder().messageType(RETRIEVE_SUBJECTS_RESPONSE)
        .subjects(subjectsWrapper).build();
  }

  private List<SubjectWrapper> createSubjectsWrapper(Set<SubjectEntity> subjects) {
    List<SubjectWrapper> result = new ArrayList<>();
    for (SubjectEntity subjectEntity : subjects) {
      Set<InstructorEntity> instructorSet =
          instructorRepository.getInstructorsBySubjectId(subjectEntity.getId());
      List<InstructorWrapper> instructors = convertEntitiesToWrapper(instructorSet);
      result.add(SubjectWrapper.builder().subjectName(subjectEntity.getName())
          .instructors(instructors).build());
    }
    return result;
  }

  private List<InstructorWrapper> convertEntitiesToWrapper(Set<InstructorEntity> instructorSet) {
    return instructorSet.stream().map(this::toInstructorWrapper).collect(Collectors.toList());
  }

  private InstructorWrapper toInstructorWrapper(InstructorEntity entity) {
    return InstructorWrapper.builder().name(entity.getName()).instructorId(entity.getId()).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return RetrieveSubjectsRequest.class;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String getBeanName() {
    return BEAN_NAME;
  }



}
