package hu.unideb.smartcampus.service.api.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.component.VEvent;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import hu.unideb.smartcampus.service.api.CalendarEventType;
import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarAppointmentDetailsParser;
import hu.unideb.smartcampus.service.api.calendar.parser.CalendarSubjectDetailsParser;
import hu.unideb.smartcampus.service.api.exception.InputParseException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;
import hu.unideb.smartcampus.webservice.api.provider.HttpResponseProvider;

@Service
public class CalendarServiceImpl implements CalendarService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

  @Autowired
  private HttpResponseProvider httpResponseProvider;

  @Autowired
  private CalendarAppointmentDetailsParser calendarAppointmentDetailsParser;

  @Autowired
  private CalendarSubjectDetailsParser calendarSubjectDetailsParser;

  @Override
  public List<SubjectEvent> downloadCalendar(String urlToParse) throws InputParseException {

    HttpResponse httpResponse;
    try {
      httpResponse = httpResponseProvider.sendHttpRequest(urlToParse, HttpRequestType.HTTP_REQUEST_GET);
    } catch (final IOException e) {
      throw new InputParseException(e);
    }
    final LinkedList<SubjectEvent> subjectEvents = Lists.newLinkedList();
    try (final InputStream is = httpResponse.getEntity().getContent()) {
      for (Object prop : new CalendarBuilder().build(is).getComponents()) {
          final VEvent event = (VEvent) prop;
          try {
            if (CalendarEventType.matches(event.getSummary().getValue()) == CalendarEventType.TIMETABLE) {
              final SubjectDetails subjectDetails = this.calendarSubjectDetailsParser.parseSubjectDetails(event);
              final AppointmentTime appointmentTime = this.calendarAppointmentDetailsParser.parseAppointmentDetails(event);

              subjectBuilderListPopulator(subjectEvents, SubjectEvent.builder()
                  .subjectDetails(subjectDetails)
                  .roomLocation(event.getLocation().getValue())
                  .appointmentTimeList(Lists.newArrayList())
                  .build(),
                  appointmentTime);
            }
          } catch (UnparsableCalendarEventSummaryException e) {
            throw new InputParseException(e);
          }
      }
    } catch (final IOException | ParserException e) {
      throw new InputParseException(e);
    }
    LOGGER.info("============================================================\n\n\n");
    LOGGER.info("value: {}", subjectEvents);
    return subjectEvents;
  }

  private void subjectBuilderListPopulator(final LinkedList<SubjectEvent> subjectEvents, final SubjectEvent subjectEvent, final AppointmentTime appointmentTime) {
    final Optional<SubjectEvent> subjectEventOptional = subjectEvents.parallelStream()
        .filter(actualSubjectEvent -> actualSubjectEvent.equals(subjectEvent))
        .findFirst();

    if (subjectEventOptional.isPresent()) {
      subjectEventOptional.get().getAppointmentTimeList().add(appointmentTime);
    } else {
      subjectEvent.getAppointmentTimeList().add(appointmentTime);
      subjectEvents.add(subjectEvent);
    }
  }

}