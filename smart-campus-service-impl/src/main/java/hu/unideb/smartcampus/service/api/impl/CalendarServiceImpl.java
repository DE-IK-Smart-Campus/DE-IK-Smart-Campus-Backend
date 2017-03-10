package hu.unideb.smartcampus.service.api.impl;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import hu.unideb.smartcampus.service.api.CaledarEventSummary;
import hu.unideb.smartcampus.service.api.CalendarEventType;
import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.CalendarSummaryParser;
import hu.unideb.smartcampus.service.api.UnparsableCalendarEventSummaryException;
import hu.unideb.smartcampus.service.api.exception.InputParseException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;
import hu.unideb.smartcampus.webservice.api.provider.HttpResponseProvider;

@Service
public class CalendarServiceImpl implements CalendarService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);

  @Autowired
  private HttpResponseProvider httpResponseProvider;

  @Autowired
  private CalendarSummaryParser calendarSummaryParser;

  @Override
  public void downloadCalendar(String urlToParse) throws InputParseException {

    HttpResponse httpResponse;
    try {
      httpResponse =
          httpResponseProvider.sendHttpRequest(urlToParse, HttpRequestType.HTTP_REQUEST_GET);
    } catch (IOException e) {
      throw new InputParseException(e);
    }


    try (InputStream is = httpResponse.getEntity().getContent()) {
      CalendarBuilder calendarBuilder = new CalendarBuilder();
      Calendar calendar;
      calendar = calendarBuilder.build(is);
      for (Object prop : calendar.getComponents()) {
        if (prop instanceof VEvent) {
          VEvent event = (VEvent) prop;
          try {
            final String eventSummary = event.getSummary().getValue();
            if (CalendarEventType.matches(eventSummary) == CalendarEventType.TIMETABLE) {
              CaledarEventSummary summary = calendarSummaryParser.parseSummary(eventSummary);
              LocalDateTime startDate =  LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getStartDate().getDate().getTime()),
                  ZoneId.systemDefault());
              LocalDateTime endDate =  LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getEndDate(true).getDate().getTime()), ZoneId
                  .systemDefault());

              LOGGER.info(summary.toString());
              LOGGER.info("Start date: {}", startDate);
              LOGGER.info("End date: {}", endDate);
            }
          } catch (UnparsableCalendarEventSummaryException e) {
            throw new InputParseException(e);
          }
        }
      }
    } catch (IOException | ParserException e) {
      throw new InputParseException(e);
    }

  }

}
