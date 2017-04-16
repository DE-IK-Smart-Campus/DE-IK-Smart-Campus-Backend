package hu.unideb.smartcampus.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Resource;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.service.api.converter.CalendarSubjectListConverter;
import hu.unideb.smartcampus.service.api.converter.ListCustomEventConverter;
import hu.unideb.smartcampus.service.api.iq.AddCustomEventIqHandler;
import hu.unideb.smartcampus.service.api.iq.CalendarSubjectsIqHandler;
import hu.unideb.smartcampus.service.api.iq.DeleteCustomEventIqHandler;
import hu.unideb.smartcampus.service.api.iq.ListCustomEventIqHandler;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

@Service
public class CalendarServiceImpl implements CalendarService {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

  /**
   * Domain for smartcampus user.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  private String domain;

  /**
   * Ejabberd user.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  @Override
  public List<CalendarEvent> getCustomEvents() {
    final ListCustomEventIqHandler iqHandler = new ListCustomEventIqHandler(ejabberdUser.getConnection(), domain);
    final ListCustomEventIqRequest iqRequest = iqHandler.getResult();
    final Converter<ListCustomEventIqRequest, List<CalendarEvent>> converter = new ListCustomEventConverter();
    return converter.convert(iqRequest);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<CalendarEvent> getCalendarSubjectEvents(Long startPeriod, Long endPeriod) {
    LOGGER.info("Getting calendar subjects for student.");
    final CalendarSubjectsIqHandler iqHandler = new CalendarSubjectsIqHandler(ejabberdUser.getConnection(), domain, startPeriod, endPeriod);
    final CalendarSubjectsIqRequest iqRequest = iqHandler.getResult();
    final Converter<List<CalendarSubjectIqElement>, List<CalendarSubject>> converter = new CalendarSubjectListConverter();
    LOGGER.info("IQ request subject event size: {}", iqRequest.getSubjectEvents().size());
    final List<CalendarSubject> convert = converter.convert(iqRequest.getSubjectEvents());
    return convert.stream().flatMap(this::buildCalendarEventsFromCalendarSubject)
        .collect(Collectors.toList());
  }

  @Override
  public void addCustomEvent(CustomEventIqElement customEvent) {
    final AddCustomEventIqHandler iqHandler = new AddCustomEventIqHandler(ejabberdUser.getConnection(), domain, customEvent);
    iqHandler.getResult();
  }

  @Override
  public void deleteCustomEvent(String eventGuid) {
    final DeleteCustomEventIqHandler iqHandler = new DeleteCustomEventIqHandler(ejabberdUser.getConnection(), domain, eventGuid);
    iqHandler.getResult();
  }

  private Stream<CalendarEvent> buildCalendarEventsFromCalendarSubject(CalendarSubject calendarSubject) {
    return calendarSubject.getAppointmentTimes().stream()
        .map(appointmentTime -> {
              LOGGER.info(
                  "Building calendar event with when, from and to: {} - {} - {}",
                  appointmentTime.getWhen().toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME),
                  appointmentTime.getFrom(),
                  appointmentTime.getTo()
              );
              return new CalendarEvent(
                  calendarSubject.getSubjectName(),
                  appointmentTime.getWhen().toLocalDateTime().withHour(0).plusNanos(appointmentTime.getFrom()).format(DateTimeFormatter.ISO_DATE_TIME),
                  appointmentTime.getWhen().toLocalDateTime().withHour(0).plusNanos(appointmentTime.getTo()).format(DateTimeFormatter.ISO_DATE_TIME)
              );
            }
        );
  }
}
