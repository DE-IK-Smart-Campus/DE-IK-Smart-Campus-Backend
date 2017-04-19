package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.domain.calendar.CalendarSubject;

public interface AttendanceService {
  List<CalendarSubject> listSubjectsWithAttendance();
  CalendarSubject getSubjectWithAttendanceByName(String name);
  void updateAppointmentById(Long id, Boolean present);
}
