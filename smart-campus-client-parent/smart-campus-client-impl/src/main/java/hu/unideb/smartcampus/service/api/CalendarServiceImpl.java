package hu.unideb.smartcampus.service.api;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.service.api.converter.CalendarSubjectListConverter;
import hu.unideb.smartcampus.service.api.iq.CalendarSubjectsIqHandler;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

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

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<CalendarSubject> getCalendarSubjects(Long startPeriod, Long endPeriod) {
    LocalDateTime now = LocalDateTime.now();
    LOGGER.info("Getting calendar subjects for student");
    LOGGER.info("Request started:{}", now);
    final CalendarSubjectsIqHandler iqHandler =
        new CalendarSubjectsIqHandler(ejabberdUser.getConnection(), domain, startPeriod, endPeriod);
    final CalendarSubjectsIqRequest iqRequest = iqHandler.getResult();
    LOGGER.info("Response arrived at:{} dev:{}", LocalDateTime.now(),
        ChronoUnit.MILLIS.between(LocalDateTime.now(), now));
    final Converter<List<CalendarSubjectIqElement>, List<CalendarSubject>> converter =
        new CalendarSubjectListConverter();
    now = LocalDateTime.now();
    LOGGER.info("Conversion started at:{}", now);
    List<CalendarSubject> convert = converter.convert(iqRequest.getSubjectEvents());
    LOGGER.info("Conversion ended at:{} dev:{}", LocalDateTime.now(),
        ChronoUnit.MILLIS.between(LocalDateTime.now(), now));
    return convert;
  }
}
