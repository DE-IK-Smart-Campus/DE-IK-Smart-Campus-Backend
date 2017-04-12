package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.StudentTimeTableInfo;
import hu.unideb.smartcampus.shared.exception.InputParseException;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

/**
 * A service for Calendar realted operations.
 *
 */
public interface CalendarService {

  /**
   * Downloads, parses and processes the .ics file given.
   * 
   * @param url of the .ics file to be parsed
   * @throws InputParseException if any problem occours during the process
   */
  List<SubjectEvent> downloadCalendar(String url) throws InputParseException;
  
  /**
   * Download student time table and convert it to subject event.
   * @param studentTimeTable student time table from Neptun.
   */
  StudentTimeTableInfo downloadStudentTimeTable(StudentTimeTable studentTimeTable);

}
