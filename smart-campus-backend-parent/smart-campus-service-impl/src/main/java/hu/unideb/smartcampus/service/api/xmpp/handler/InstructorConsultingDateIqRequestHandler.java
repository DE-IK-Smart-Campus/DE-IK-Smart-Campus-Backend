package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.request.service.RetrieveInstructorsConsultingHoursRequestServiceImpl;
import hu.unideb.smartcampus.shared.iq.request.AbstractSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveInstructorConsultingHours;
import hu.unideb.smartcampus.shared.wrapper.InstructorConsultingHoursWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.ConsultingDateWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.FromToDateWrapper;


/**
 * Consulting date handler.
 */
@Service
public class InstructorConsultingDateIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  @Qualifier(RetrieveInstructorsConsultingHoursRequestServiceImpl.BEAN_NAME)
  private MessageProcessingClass<InstructorConsultingHoursWrapper> service;

  /**
   * Ctor.
   */
  public InstructorConsultingDateIqRequestHandler() {
    super(InstructorConsultingDatesIqRequest.ELEMENT, AbstractSmartCampusIq.BASE_NAMESPACE,
        Type.get, Mode.sync);
  }

  /**
   * Ctor.
   */
  protected InstructorConsultingDateIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    InstructorConsultingDatesIqRequest iq =
        (InstructorConsultingDatesIqRequest) super.handleIQRequest(iqRequest);
    String instructorId = iq.getInstructorId();
    RetrieveInstructorConsultingHours request = RetrieveInstructorConsultingHours.builder()
        .instructorId(Long.valueOf(instructorId)).build();
    InstructorConsultingHoursWrapper response = service.getResponse(request);
    List<ConsultingDateWrapper> consultingHours = response.getConsultingHours();
    List<ConsultingDateIqElement> consultingDates =
        consultingHours.stream().map(this::toIqElement).collect(Collectors.toList());
    iq.setConsultingDates(consultingDates);
    return iq;
  }

  private ConsultingDateIqElement toIqElement(ConsultingDateWrapper wrapper) {
    return ConsultingDateIqElement.builder().consultingHourId(wrapper.getConsultingDateId())
        .reservedSum(wrapper.getReservedSum())
        .fromToDates(convertFromToDate(wrapper.getFromToDates())).build();
  }

  private FromToDateIqElement convertFromToDate(FromToDateWrapper fromToDates) {
    return FromToDateIqElement.builder().from(fromToDates.getFrom()).to(fromToDates.getTo())
        .build();
  }

}
