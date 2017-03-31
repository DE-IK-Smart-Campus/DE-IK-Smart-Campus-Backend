package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.request.service.RetrieveInstructorsConsultingDatesRequestService;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;
import hu.unideb.smartcampus.shared.wrapper.inner.ConsultingDateWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.FromToDateWrapper;


/**
 * Consulting date handler.
 */
@Service
public class InstructorConsultingDateIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private RetrieveInstructorsConsultingDatesRequestService service;

  /**
   * Ctor.
   */
  public InstructorConsultingDateIqRequestHandler() {
    super(InstructorConsultingDatesIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE,
        Type.get,
        Mode.async);
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
    Long instructorIdLong = Long.valueOf(instructorId);
    List<ConsultingDateWrapper> consultingHours =
        service.getConsultingDatesByInstructorId(instructorIdLong);
    List<ConsultingDateIqElement> consultingDates =
        consultingHours.stream().map(this::toIqElement).collect(Collectors.toList());
    String instructorName = service.getInstructorNameById(instructorIdLong);
    iq.setConsultingDates(consultingDates);
    iq.setInstructorName(instructorName);
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
