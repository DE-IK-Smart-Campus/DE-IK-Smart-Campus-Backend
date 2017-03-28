package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;


/**
 * Calendar subject retrieval handler.
 */
@Service
public class CalendarSubjectsIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  private static final ZoneOffset HUNGARIAN_OFFSET = ZoneOffset.ofHours(1);
  
  @Autowired
  private SubjectEventService subjectEventService;

  /**
   * Ctor.
   */
  public CalendarSubjectsIqRequestHandler() {
    super(CalendarSubjectsIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.get,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected CalendarSubjectsIqRequestHandler(String element, String namespace, Type type,
      Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    CalendarSubjectsIqRequest iq = (CalendarSubjectsIqRequest) super.handleIQRequest(iqRequest);
    List<CalendarSubjectIqElement> subjectEvents = getSubjectEvents(iq);
    iq.setSubjectEvents(subjectEvents);
    return iq;
  }

  private List<CalendarSubjectIqElement> getSubjectEvents(CalendarSubjectsIqRequest iq) {
    List<CalendarSubjectIqElement> subjectEvents = new ArrayList<>();
    List<SubjectEvent> allSubjectEventByUsername =
        subjectEventService.getAllSubjectEventByUsername(iq.getStudent());
    for (SubjectEvent subjectEvent : allSubjectEventByUsername) {
      subjectEvents.add(buildIqElement(subjectEvent));
    }
    return subjectEvents;
  }

  private CalendarSubjectIqElement buildIqElement(SubjectEvent subjectEvent) {
    return CalendarSubjectIqElement.builder()
        .subjectName(subjectEvent.getSubjectDetails().getSubjectName())
        .where(subjectEvent.getRoomLocation())
        .appointmentTimes(convertToIqElement(subjectEvent.getAppointmentTimeList()))
        .description(createDescriptionByTeachers(subjectEvent)).build();
  }

  private String createDescriptionByTeachers(SubjectEvent subjectEvent) {
    StringBuilder builder = new StringBuilder();
    List<String> teacherNames = subjectEvent.getSubjectDetails().getTeacherNames();
    build(builder, teacherNames);
    return builder.toString();
  }

  private void build(StringBuilder builder, List<String> teacherNames) {
    Integer counter = 0;
    for (String teacher : teacherNames) {
      counter++;
      builder.append(teacher).append(appendColonOrWhiteSpace(teacherNames, counter));
    }
  }

  private String appendColonOrWhiteSpace(List<String> teacherNames, Integer counter) {
    return counter.equals(teacherNames.size()) ? "" : ", ";
  }

  private List<AppointmentTimeIqElement> convertToIqElement(
      List<AppointmentTime> appointmentTimeList) {
    return appointmentTimeList.stream().map(this::convertToIqElement).collect(Collectors.toList());
  }

  private AppointmentTimeIqElement convertToIqElement(AppointmentTime appointmentTime) {
    return AppointmentTimeIqElement.builder()
        .from(appointmentTime.getStartDateTime().toEpochSecond(HUNGARIAN_OFFSET))
        .to(appointmentTime.getEndDateTime().toEpochSecond(HUNGARIAN_OFFSET)).when(appointmentTime
            .getStartDateTime().toLocalDate().atStartOfDay().toEpochSecond(HUNGARIAN_OFFSET))
        .build();
  }
}
