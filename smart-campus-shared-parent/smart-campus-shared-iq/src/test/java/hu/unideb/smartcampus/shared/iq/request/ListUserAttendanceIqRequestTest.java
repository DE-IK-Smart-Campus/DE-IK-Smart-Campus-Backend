package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.ListUserAttendanceIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * List attendance IQ parser test.
 */
public class ListUserAttendanceIqRequestTest extends AbstractParserTest {

  private static final long ID2 = 2L;

  private static final long ID = 1L;

  private static final int FIRST_ELEMENT_INDEX = 0;

  private static final Long WHEN = 123L;

  private static final Long TO = 10L;

  private static final Long FROM = 1L;

  private static final AppointmentTimeIqElement APPOINTMENT_TIME_IQ_ELEMENT =
      AppointmentTimeIqElement.builder()
          .from(FROM)
          .to(TO)
          .when(WHEN)
          .id(ID)
          .present(true)
          .build();

  private static final AppointmentTimeIqElement APPOINTMENT_TIME_IQ_ELEMENT_SECOND =
      AppointmentTimeIqElement.builder()
          .from(FROM + TO)
          .to(TO + TO)
          .when(WHEN)
          .id(ID2)
          .present(false)
          .build();

  private static final String WHERE = "Room";

  private static final String DESCRIPTION = "description";

  private static final String STUDENT = "ExampleStudent";

  private static final String SUBJECTNAME = "AI";

  private static final List<AppointmentTimeIqElement> APPOINTMENTTIMES =
      Arrays.asList(APPOINTMENT_TIME_IQ_ELEMENT, APPOINTMENT_TIME_IQ_ELEMENT_SECOND);

  private static final List<CalendarSubjectIqElement> SUBJECTEVENTS =
      Arrays.asList(CalendarSubjectIqElement.builder().subjectName(SUBJECTNAME)
          .appointmentTimes(APPOINTMENTTIMES)
          .description(DESCRIPTION)
          .where(WHERE).build());

  @Test
  public void testIqProvider() throws Exception {
    ListUserAttendanceIqRequest iq =
        ListUserAttendanceIqRequest.builder()
            .student(STUDENT)
            .subjectEvents(SUBJECTEVENTS)
            .build();
    ListUserAttendanceIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(SUBJECTEVENTS, parse.getSubjectEvents());
    Assert.assertEquals(APPOINTMENTTIMES,
        parse.getSubjectEvents().get(FIRST_ELEMENT_INDEX).getAppointmentTimes());
  }

  @Override
  public String getElement() {
    return ListUserAttendanceIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new ListUserAttendanceIqProvider();
  }

}
