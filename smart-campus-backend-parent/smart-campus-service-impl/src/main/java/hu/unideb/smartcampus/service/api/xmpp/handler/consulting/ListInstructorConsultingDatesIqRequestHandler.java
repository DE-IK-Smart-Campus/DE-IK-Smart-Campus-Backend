package hu.unideb.smartcampus.service.api.xmpp.handler.consulting;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UserConsultingDateService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListInstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;


/**
 * List instructor consulting dates with signed users.
 */
@Service
public class ListInstructorConsultingDatesIqRequestHandler
    extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private UserConsultingDateService service;

  /**
   * Ctor.
   */
  public ListInstructorConsultingDatesIqRequestHandler() {
    super(ListInstructorConsultingDatesIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE,
        Type.get,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected ListInstructorConsultingDatesIqRequestHandler(String element, String namespace,
      Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    ListInstructorConsultingDatesIqRequest iq =
        (ListInstructorConsultingDatesIqRequest) super.handleIQRequest(iqRequest);
    Long instructorId = iq.getInstructorId();
    List<StudentIqElement> students = getStudents(iq, instructorId);
    iq.setStudents(students);
    return iq;
  }

  private List<StudentIqElement> getStudents(ListInstructorConsultingDatesIqRequest iq,
      Long instructorId) {
    return iq.isOneWeek()
        ? service.findSignedStudentByInstructorIdWithinOneWeek(iq.getInstructorId())
        : service.listSignedStudentByInstructorId(instructorId);
  }

}
