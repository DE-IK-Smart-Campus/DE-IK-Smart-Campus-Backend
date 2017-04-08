package hu.unideb.smartcampus.service.api.impl;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

/**
 * Test for {@linkplain CalendarServiceImpl}
 * 
 * @author Nandi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CalendarServiceImplTest {

  CalendarServiceImpl service = new CalendarServiceImpl();


  @Test
  public void donwloadStudentTimeTable() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("src/test/resources/testTimeTable.json");
    StudentTimeTable timeTable = mapper.readValue(file, StudentTimeTable.class);
    List<SubjectEvent> donwloadStudentTimeTable = service.downloadStudentTimeTable(timeTable);
    System.out.println(donwloadStudentTimeTable.size());
  }

}
