package hu.unideb.smartcampus.service.api;

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
    LOGGER.info("Getting calendar subjects for student.");
    final CalendarSubjectsIqHandler iqHandler =
        new CalendarSubjectsIqHandler(ejabberdUser.getConnection(), domain, startPeriod, endPeriod);
    final CalendarSubjectsIqRequest iqRequest = iqHandler.getResult();
    final Converter<List<CalendarSubjectIqElement>, List<CalendarSubject>> converter =
        new CalendarSubjectListConverter();
    List<CalendarSubject> convert = converter.convert(iqRequest.getSubjectEvents());
    return convert;
  }
}
