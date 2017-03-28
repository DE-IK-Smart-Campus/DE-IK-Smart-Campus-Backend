package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectType;
import hu.unideb.smartcampus.shared.iq.request.CalendarSubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.AppointmentTimeIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Test CalendarSubjectsIqRequestHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalendarSubjectsIqRequestHandlerTest {

  /**
   * Date zone offset.
   */
  private static final ZoneOffset HUNGARIAN_OFFSET = ZoneOffset.ofHours(1);

  /**
   * Room location.
   */
  private static final String ROOM_LOCATION = "IF-01";

  /**
   * Subject name.
   */
  private static final String SUBJECT_NAME = "AI";

  /**
   * Instructor.
   */
  private static final String GEORGE = "George";

  /**
   * Instructor.
   */
  private static final String JOHN = "John";

  /**
   * Teacher names.
   */
  private static final List<String> TEACHER_NAMES = Arrays.asList(JOHN, GEORGE);

  /**
   * First appointment.
   */
  private static final AppointmentTime FIRST_APPOINTMENT =
      AppointmentTime.builder()
          .startDateTime(LocalDateTime.of(2017, 3, 30, 8, 0, 0))
          .endDateTime(LocalDateTime.of(2017, 3, 30, 10, 0, 0))
          .build();

  /**
   * Second appointment.
   */
  private static final AppointmentTime SECOND_APPOINTMENT =
      AppointmentTime.builder()
          .startDateTime(LocalDateTime.of(2017, 3, 31, 12, 0, 0))
          .endDateTime(LocalDateTime.of(2017, 3, 31, 14, 0, 0))
          .build();

  /**
   * Test user.
   */
  private static final String USERNAME = "TestUser";

  /**
   * Subject details.
   */
  private static final SubjectDetails SUBJECTDETAILS =
      SubjectDetails.builder()
          .subjectName(SUBJECT_NAME)
          .subjectType(SubjectType.LABORATORY)
          .startPeriod(LocalDate.of(2017, 2, 20))
          .endPeriod(LocalDate.of(2017, 5, 26))
          .teacherNames(TEACHER_NAMES).build();

  /**
   * Appointment list.
   */
  private static final List<AppointmentTime> APPOINTMENTTIMELIST =
      Arrays.asList(FIRST_APPOINTMENT, SECOND_APPOINTMENT);

  /**
   * Subject event list.
   */
  private static final List<SubjectEvent> SUBJECT_EVENT_LIST =
      Arrays.asList(SubjectEvent.builder()
          .id(1L)
          .roomLocation(ROOM_LOCATION)
          .subjectDetails(SUBJECTDETAILS)
          .appointmentTimeList(APPOINTMENTTIMELIST)
          .build());

  /**
   * IQ request.
   */
  private static final CalendarSubjectsIqRequest IQ_REQUEST =
      CalendarSubjectsIqRequest.builder()
          .student(USERNAME)
          .build();

  /**
   * First appointment.
   */
  private static final AppointmentTimeIqElement FIRST_APPOINTMENT_IQ_ELEMENT =
      AppointmentTimeIqElement.builder()
          .from(LocalDateTime.of(2017, 3, 30, 8, 0, 0).toEpochSecond(HUNGARIAN_OFFSET))
          .to(LocalDateTime.of(2017, 3, 30, 10, 0, 0).toEpochSecond(HUNGARIAN_OFFSET))
          .when(LocalDateTime.of(2017, 3, 30, 8, 0, 0).toLocalDate().atStartOfDay()
              .toEpochSecond(HUNGARIAN_OFFSET))
          .build();

  /**
   * Second appointment.
   */
  private static final AppointmentTimeIqElement SECOND_APPOINTMENT_IQ_ELEMENT =
      AppointmentTimeIqElement.builder()
          .from(LocalDateTime.of(2017, 3, 31, 12, 0, 0).toEpochSecond(HUNGARIAN_OFFSET))
          .to(LocalDateTime.of(2017, 3, 31, 14, 0, 0).toEpochSecond(HUNGARIAN_OFFSET))
          .when(LocalDateTime.of(2017, 3, 31, 12, 0, 0).toLocalDate().atStartOfDay()
              .toEpochSecond(HUNGARIAN_OFFSET))
          .build();

  /**
   * Appointment times.
   */
  private static final List<AppointmentTimeIqElement> APPOINTMENTTIMES =
      Arrays.asList(FIRST_APPOINTMENT_IQ_ELEMENT, SECOND_APPOINTMENT_IQ_ELEMENT);

  /**
   * Expected calendar event.
   */
  private static final CalendarSubjectIqElement EXPECTED_CALENDAR_EVENT =
      CalendarSubjectIqElement.builder()
          .description(JOHN + ", " + GEORGE)
          .subjectName(SUBJECT_NAME)
          .where(ROOM_LOCATION)
          .appointmentTimes(APPOINTMENTTIMES)
          .build();

  /**
   * Expected list.
   */
  private static final List<CalendarSubjectIqElement> EXPECTED =
      Arrays.asList(EXPECTED_CALENDAR_EVENT);

  /**
   * Handler.
   */
  @InjectMocks
  private CalendarSubjectsIqRequestHandler handler = new CalendarSubjectsIqRequestHandler();

  /**
   * Subject event service.
   */
  @Mock
  private SubjectEventService subjectEventService;

  /**
   * Test handler.
   */
  @Test
  public void testHandleIQRequest() {
    // given

    // when
    Mockito.when(subjectEventService.getAllSubjectEventByUsername(USERNAME))
        .thenReturn(SUBJECT_EVENT_LIST);

    CalendarSubjectsIqRequest iqRequest =
        (CalendarSubjectsIqRequest) handler.handleIQRequest(IQ_REQUEST);

    List<CalendarSubjectIqElement> subjectEvents = iqRequest.getSubjectEvents();
    Assert.assertEquals(EXPECTED, subjectEvents);

  }

}
