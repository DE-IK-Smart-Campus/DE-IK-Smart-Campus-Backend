package hu.unideb.smartcampus.service.api.xmpp.handler.attendance;

import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.CalendarSubjectService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListUserAttendanceIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;


/**
 * User attendance handler.
 */
@Service
public class ListUserAttendanceIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private CalendarSubjectService calendarSubjectService;

  /**
   * Ctor.
   */
  public ListUserAttendanceIqRequestHandler() {
    super(ListUserAttendanceIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected ListUserAttendanceIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    ListUserAttendanceIqRequest iq = (ListUserAttendanceIqRequest) super.handleIQRequest(iqRequest);
    List<CalendarSubjectIqElement> subjectEvents =
        calendarSubjectService.getSubjectEventsWithinPeriod(iq);
    iq.setSubjectEvents(subjectEvents);
    return iq;
  }

}
