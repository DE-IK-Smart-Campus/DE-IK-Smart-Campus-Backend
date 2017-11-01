package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.CalendarSubjectsIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Calendar subjects IQ parser test.
 */
public class CalendarSubjectsIqRequestTest extends AbstractParserTest {

  private static final Long START_PERIOD = 123456L;
  
  private static final Long END_PERIOD = 654321L;
  
  private static final int FIRST_ELEMENT_INDEX = 0;
  
  private static final Long WHEN = 123L;
  
  private static final Long TO = 10L;
  
  private static final Long FROM = 1L;
  
  private static final AppointmentTimeIqElement APPOINTMENT_TIME_IQ_ELEMENT =
      AppointmentTimeIqElement.builder().from(FROM).to(TO).when(WHEN).build();
  
  private static final AppointmentTimeIqElement APPOINTMENT_TIME_IQ_ELEMENT_SECOND =
      AppointmentTimeIqElement.builder().from(FROM + TO).to(TO + TO).when(WHEN).build();
  
  private static final String WHERE = "Room";
  
  private static final String DESCRIPTION = "description";
  
  private static final String STUDENT = "ExampleStudent";
  

  private static final Long SUBJECT_ID = 100L;

  private static final String SUBJECTNAME = "AI";
  
  private static final List<AppointmentTimeIqElement> APPOINTMENTTIMES =
      Arrays.asList(APPOINTMENT_TIME_IQ_ELEMENT, APPOINTMENT_TIME_IQ_ELEMENT_SECOND);
  
  private static final List<CalendarSubjectIqElement> SUBJECTEVENTS =
      Arrays.asList(CalendarSubjectIqElement.builder().id(SUBJECT_ID).subjectName(SUBJECTNAME)
          .appointmentTimes(APPOINTMENTTIMES).description(DESCRIPTION).where(WHERE).id(SUBJECT_ID).build());

  @Test
  public void testIqProvider() throws Exception {
    CalendarSubjectsIqRequest iq =
        CalendarSubjectsIqRequest.builder().student(STUDENT).subjectEvents(SUBJECTEVENTS).startPeriod(START_PERIOD).endPeriod(END_PERIOD).build();
    CalendarSubjectsIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(SUBJECTEVENTS, parse.getSubjectEvents());
    Assert.assertEquals(APPOINTMENTTIMES,
        parse.getSubjectEvents().get(FIRST_ELEMENT_INDEX).getAppointmentTimes());
    Assert.assertEquals(START_PERIOD, parse.getStartPeriod());
    Assert.assertEquals(END_PERIOD, parse.getEndPeriod());
  }

  @Override
  public String getElement() {
    return CalendarSubjectsIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new CalendarSubjectsIqProvider();
  }

}
