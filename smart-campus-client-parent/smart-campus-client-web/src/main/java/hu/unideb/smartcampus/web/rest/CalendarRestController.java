package hu.unideb.smartcampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarEvent;
import hu.unideb.smartcampus.service.api.CalendarService;

/**
 * Calendar REST controller.
 */
@RestController
@RequestMapping("/calendar")
public class CalendarRestController {

  @Autowired
  private CalendarService calendarService;

  @GetMapping("/subject-event")
  public List<CalendarEvent> getCalendarSubjectEventList() {
    return calendarService.getCalendarSubjectEvents(
        LocalDate.of(2017, 1, 31).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(2)),
        LocalDate.of(2017, 5, 31).atStartOfDay().toEpochSecond(ZoneOffset.ofHours(2)));
  }
}
