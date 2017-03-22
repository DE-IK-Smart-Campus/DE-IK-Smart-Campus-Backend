package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveSubjectsRequest;
import hu.unideb.smartcampus.shared.wrapper.SubjectRetrievalResponseWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.InstructorWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.SubjectWrapper;


/**
 * Subject retrieval.
 */
@Service
public class SubjectRequestIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  @Qualifier("retrieveSubjectsRequestServiceImpl")
  private MessageProcessingClass<SubjectRetrievalResponseWrapper> service;

  /**
   * Ctor.
   */
  public SubjectRequestIqRequestHandler() {
    super(SubjectsIqRequest.ELEMENT, BaseSmartCampusIq.BASE_NAMESPACE, Type.get, Mode.sync);
  }

  /**
   * Ctor.
   */
  protected SubjectRequestIqRequestHandler(String element, String namespace, Type type, Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    SubjectsIqRequest iq = (SubjectsIqRequest) super.handleIQRequest(iqRequest);
    String student = iq.getStudent();
    List<SubjectIqElement> subjectsList = getSubjectList(student);
    iq.setSubjects(subjectsList);
    return iq;
  }

  private List<SubjectIqElement> getSubjectList(String student) {
    RetrieveSubjectsRequest request = RetrieveSubjectsRequest.builder().userId(student).build();
    SubjectRetrievalResponseWrapper response = service.getResponse(request);
    List<SubjectWrapper> subjects = response.getSubjects();
    List<SubjectIqElement> subjectsList = new ArrayList<>();
    for (SubjectWrapper subjectWrapper : subjects) {
      List<InstructorIqElement> instructors = getInstructors(subjectWrapper);
      subjectsList.add(new SubjectIqElement(subjectWrapper.getName(), instructors));
    }
    return subjectsList;
  }

  private List<InstructorIqElement> getInstructors(SubjectWrapper subjectWrapper) {
    return subjectWrapper.getInstructors().stream().map(this::toInstructor)
        .collect(Collectors.toList());
  }

  private InstructorIqElement toInstructor(InstructorWrapper wrapper) {
    return InstructorIqElement.builder().instructorId(wrapper.getInstructorId())
        .name(wrapper.getName()).build();
  }

}
