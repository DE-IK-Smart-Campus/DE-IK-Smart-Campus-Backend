package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectRetrievalResponseWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.SubjectWrapper;
import hu.unideb.smartcampus.shared.iq.SubjectsIq;
import hu.unideb.smartcampus.shared.iq.SubjectsIq.Subject;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveSubjectsRequest;

/**
 * Subject retrieval.
 *
 */
@Service
@Qualifier("subjectIqRequestHandler")
public class SubjectIqRequestHandler extends AbstractIqRequestHandler {

  @Autowired
  @Qualifier("retrieveSubjectsRequestServiceImpl")
  private MessageProcessingClass<SubjectRetrievalResponseWrapper> service;

  /**
   * Ctor.
   */
  public SubjectIqRequestHandler() {
    super(SubjectsIq.ELEMENT, SubjectsIq.NAMESPACE, Type.get, Mode.sync);
  }

  /**
   * Ctor.
   */
  protected SubjectIqRequestHandler(String element, String namespace, Type type, Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    SubjectsIq iq = (SubjectsIq) iqRequest;
    iq.setType(Type.result);
    String student = iq.getStudent();
    RetrieveSubjectsRequest request = RetrieveSubjectsRequest.builder().userId(student).build();
    SubjectRetrievalResponseWrapper response = service.getResponse(request);
    List<SubjectWrapper> subjects = response.getSubjects();
    List<Subject> subjectsList = new ArrayList<>();
    for (SubjectWrapper subjectWrapper : subjects) {
      subjectsList.add(new Subject(subjectWrapper.getName()));
    }
    iq.setSubjects(subjectsList);
    return iq;
  }

}
