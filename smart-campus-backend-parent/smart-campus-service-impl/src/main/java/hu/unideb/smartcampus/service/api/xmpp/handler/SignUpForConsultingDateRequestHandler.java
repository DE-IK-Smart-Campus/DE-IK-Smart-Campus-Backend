package hu.unideb.smartcampus.service.api.xmpp.handler;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.wrapper.SignUpForConsultingHourWrapper;


/**
 * Sign up for consulting date request handler.
 */
@Service
public class SignUpForConsultingDateRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  @Qualifier("signUpForConsultingHourRequestServiceImpl")
  private MessageProcessingClass<SignUpForConsultingHourWrapper> service;

  /**
   * Ctor.
   */
  public SignUpForConsultingDateRequestHandler() {
    super(SignUpForConsultingDateIqRequest.ELEMENT, BaseSmartCampusIq.BASE_NAMESPACE, Type.set,
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
    SignUpForConsultingHourWrapper response = service.getResponse(request);
    iq.setResponseMessage(response.getStatus());
    return iq;
  }


}