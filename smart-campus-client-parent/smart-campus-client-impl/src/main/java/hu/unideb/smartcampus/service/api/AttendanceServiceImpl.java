package hu.unideb.smartcampus.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;
import hu.unideb.smartcampus.service.api.converter.calendar.CalendarSubjectListConverter;
import hu.unideb.smartcampus.service.api.iq.attendance.ChangeAttendanceIqHandler;
import hu.unideb.smartcampus.service.api.iq.attendance.ListUserAttendanceIqHandler;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.ListUserAttendanceIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

@Service
public class AttendanceServiceImpl implements AttendanceService {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceServiceImpl.class);

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
  public List<CalendarSubject> listSubjectsWithAttendance() {
    final ListUserAttendanceIqHandler iqHandler = new ListUserAttendanceIqHandler(ejabberdUser.getConnection(), domain);
    final ListUserAttendanceIqRequest iqRequest = iqHandler.getResult();
    final Converter<List<CalendarSubjectIqElement>, List<CalendarSubject>> converter = new CalendarSubjectListConverter();
    return converter.convert(iqRequest.getSubjectEvents());
  }

  @Override
  public CalendarSubject getSubjectWithAttendanceById(Long id) {
    return listSubjectsWithAttendance().stream()
        .filter(calendarSubject -> calendarSubject.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void updateAppointmentById(Long id, Boolean present) {
    final ChangeAttendanceIqHandler iqHandler = new ChangeAttendanceIqHandler(ejabberdUser.getConnection(), domain, id, present);
    iqHandler.getResult();
  }
}
