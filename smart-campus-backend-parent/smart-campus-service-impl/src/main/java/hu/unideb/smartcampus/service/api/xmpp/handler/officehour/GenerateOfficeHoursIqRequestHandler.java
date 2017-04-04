package hu.unideb.smartcampus.service.api.xmpp.handler.officehour;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.domain.response.wrapper.OfficeHourResponseWrapper;
import hu.unideb.smartcampus.service.api.request.service.OfficeHourCreationRequestService;
import hu.unideb.smartcampus.service.api.xmpp.handler.AbstractSmartCampusIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.GenerateOfficeHoursIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIntervallIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIqElement;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;


/**
 * Save subjects IQ request handler.
 */
@Service
public class GenerateOfficeHoursIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  @Autowired
  private OfficeHourCreationRequestService service;

  /**
   * Class logger.
   */
  private static final Logger LOGGER =
      LoggerFactory.getLogger(GenerateOfficeHoursIqRequestHandler.class);

  /**
   * Ctor.
   */
  public GenerateOfficeHoursIqRequestHandler() {
    super(GenerateOfficeHoursIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected GenerateOfficeHoursIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    GenerateOfficeHoursIqRequest iq =
        (GenerateOfficeHoursIqRequest) super.handleIQRequest(iqRequest);
    LOGGER.info("Creating office hours for {} instructor.", iq.getInstructorId());
    CreateOfficeHoursRequest request = CreateOfficeHoursRequest.builder()
        .instructorId(iq.getInstructorId())
        .intervall(toIntervall(iq.getIntervall()))
        .officeHours(toOfficeHours(iq.getOfficeHours()))
        .build();
    OfficeHourResponseWrapper response = service.createOfficeHourByRequest(request);
    iq.setCreatedHours(response.getNumberOfGeneratedHours());
    return iq;
  }

  private List<OfficeHour> toOfficeHours(List<OfficeHourIqElement> officeHours) {
    return officeHours.stream().map(this::toOfficeHour).collect(Collectors.toList());
  }

  private OfficeHour toOfficeHour(OfficeHourIqElement element) {
    return element == null ? null : OfficeHour.builder()
        .day(element.getDay())
        .from(element.getFrom())
        .to(element.getTo())
        .build();
  }

  private OfficeHourIntervall toIntervall(OfficeHourIntervallIqElement intervall) {
    return OfficeHourIntervall.builder()
        .fromDate(new Timestamp(intervall.getFromDate()))
        .toDate(new Timestamp(intervall.getToDate()))
        .build();
  }

}

