package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;

public interface AttendanceService {
  List<CalendarSubject> listSubjectsWithAttendance();
  CalendarSubject getSubjectWithAttendanceById(Long id);
  void updateAppointmentById(Long id, Boolean present);
}
