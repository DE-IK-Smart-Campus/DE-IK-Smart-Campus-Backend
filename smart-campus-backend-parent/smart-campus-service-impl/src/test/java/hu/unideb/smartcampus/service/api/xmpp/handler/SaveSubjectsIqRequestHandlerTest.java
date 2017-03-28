package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.jivesoftware.smack.packet.IQ;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.exception.InputParseException;
import hu.unideb.smartcampus.shared.iq.request.SaveSubjectsIcsIqRequest;

/**
 * Test SubjectRequestIqRequestHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class SaveSubjectsIqRequestHandlerTest {

  /**
   * ICS example url.
   */
  private static final String URL = "http://neptun.unideb.hu/xyz";

  /**
   * Student.
   */
  private static final String STUDENT = "John";

  /**
   * Optional student domain.
   */
  private static final Optional<User> OPTIONAL_STUDENT =
      Optional.of(User.builder().username(STUDENT).subjectDetailsList(new ArrayList<>()).build());

  /**
   * IQ request.
   */
  private static final IQ IQREQUEST =
      SaveSubjectsIcsIqRequest.builder().student(STUDENT).ics(URL).build();

  /**
   * Handler.
   */
  @InjectMocks
  private SaveSubjectsIqRequestHandler handler = new SaveSubjectsIqRequestHandler();

  /**
   * Event service.
   */
  @Mock
  private SubjectEventService subjectEventService;

  /**
   * User service.
   */
  @Mock
  private UserService userService;

  /**
   * Calendar service.
   */
  @Mock
  private CalendarService calendarService;

  /**
   * Test handle with Exception thrown.
   */
  @Test
  public void testHandleIQRequestWithError() throws InputParseException {
    // given

    // when
    Mockito.when(calendarService.downloadCalendar(URL)).thenThrow(InputParseException.class);

    // then
    SaveSubjectsIcsIqRequest handleIQRequest =
        (SaveSubjectsIcsIqRequest) handler.handleIQRequest(IQREQUEST);

    Assert.assertEquals(handler.ERROR_MESSAGE, handleIQRequest.getStatusMessage());
  }

  /**
   * Test handle.
   */
  @Test
  public void testHandleIQRequest() throws InputParseException {
    // given

    // when
    Mockito.when(calendarService.downloadCalendar(URL)).thenReturn(Collections.emptyList());
    Mockito.when(userService.getByUsername(STUDENT)).thenReturn(OPTIONAL_STUDENT);
    Mockito.doNothing().when(subjectEventService).save(Collections.emptyList());
    // then
    SaveSubjectsIcsIqRequest handleIQRequest =
        (SaveSubjectsIcsIqRequest) handler.handleIQRequest(IQREQUEST);

    Assert.assertEquals(handler.SUCCESS_MESSAGE, handleIQRequest.getStatusMessage());
  }

}
