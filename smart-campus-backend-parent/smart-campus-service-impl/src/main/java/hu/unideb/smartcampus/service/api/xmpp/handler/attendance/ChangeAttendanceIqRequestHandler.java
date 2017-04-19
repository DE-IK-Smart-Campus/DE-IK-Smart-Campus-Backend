package hu.unideb.smartcampus.service.api.xmpp.handler.attendance;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.AppointmentService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ChangeAttendanceIqRequest;


/**
 * User attendance changer handler.
 */
@Service
public class ChangeAttendanceIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private AppointmentService appointmentService;

  /**
   * Ctor.
   */
  public ChangeAttendanceIqRequestHandler() {
    super(ChangeAttendanceIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected ChangeAttendanceIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    ChangeAttendanceIqRequest iq = (ChangeAttendanceIqRequest) super.handleIQRequest(iqRequest);
    appointmentService.setPresentForAppointment(iq.getAppointmentId(), iq.getPresent());
    return iq;
  }

}
