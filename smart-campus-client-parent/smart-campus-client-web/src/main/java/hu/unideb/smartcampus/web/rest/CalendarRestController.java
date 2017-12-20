package hu.unideb.smartcampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.service.api.CalendarService;

/**
 * Calendar REST controller.
 */
@RestController
@RequestMapping(path = "/calendar")
public class CalendarRestController {

  @Autowired
  private CalendarService calendarService;

  @GetMapping(path = "/events")
  public List<CalendarEvent> getEventList() {
    return Stream.concat(
        calendarService.getCalendarSubjectEvents(
            LocalDate.of(2017, 1, 31).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(2)),
            LocalDate.of(2017, 12, 31).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(2))
        ).stream(),
        calendarService.getCustomEventsAsCalendarEventList().stream()
    ).collect(Collectors.toList());
  }
}
