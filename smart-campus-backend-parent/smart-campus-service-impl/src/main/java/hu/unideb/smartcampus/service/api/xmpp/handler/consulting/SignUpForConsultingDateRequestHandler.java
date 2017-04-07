package hu.unideb.smartcampus.service.api.xmpp.handler.consulting;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.request.service.SignUpForConsultingDateRequestService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.wrapper.SignUpForConsultingHourWrapper;


/**
 * Sign up for consulting date request handler.
 */
@Service
public class SignUpForConsultingDateRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private SignUpForConsultingDateRequestService service;

  /**
   * Ctor.
   */
  public SignUpForConsultingDateRequestHandler() {
    super(SignUpForConsultingDateIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected SignUpForConsultingDateRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    SignUpForConsultingDateIqRequest iq =
        (SignUpForConsultingDateIqRequest) super.handleIQRequest(iqRequest);
    SignUpForConsultingHourRequest request =
        SignUpForConsultingHourRequest.builder().consultingHourId(iq.getConsultingHourId())
            .duration(iq.getDuration()).reason(iq.getReason()).userId(iq.getUserId()).build();
    SignUpForConsultingHourWrapper response = service.signUpByRequest(request);
    iq.setResponseMessage(response.getStatus());
    return iq;
  }


}
