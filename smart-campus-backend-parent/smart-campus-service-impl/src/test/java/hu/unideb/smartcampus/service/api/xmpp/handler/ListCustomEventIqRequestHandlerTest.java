package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Test for listing custom events handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListCustomEventIqRequestHandlerTest {

  private static final String GUID = "GUID";

  private static final String EVENT_REMINDER = "EventReminder";

  private static final LocalDateTime EVENT_START_LOCALDATETIME = LocalDateTime.now();

  private static final LocalDateTime EVENT_END_LOCALDATETIME =
      LocalDateTime.now().plus(1, ChronoUnit.HOURS);

  private static final Long EVENT_START =
      EVENT_START_LOCALDATETIME.toEpochSecond(ZoneOffset.ofHours(0));

  private static final Long EVENT_END =
      EVENT_END_LOCALDATETIME.toEpochSecond(ZoneOffset.ofHours(0));;

  private static final String EVENT_REPEAT = "EventRepeat";

  private static final String EVENT_DESCRIPTION = "EventDescription";

  private static final String EVENT_PLACE = "EventPlace";

  private static final String EVENT_NAME = "EventName";

  private static final CustomEventIqElement CUSTOM_EVENT_IQ_ELEMENT = CustomEventIqElement.builder()
      .guid(GUID)
      .eventName(EVENT_NAME)
      .eventPlace(EVENT_PLACE)
      .eventDescription(EVENT_DESCRIPTION)
      .eventRepeat(EVENT_REPEAT)
      .reminder(EVENT_REMINDER)
      .eventStart(EVENT_START)
      .eventEnd(EVENT_END)
      .build();

  private static final List<CustomEventIqElement> CUSTOMEVENTS =
      Arrays.asList(CUSTOM_EVENT_IQ_ELEMENT);

  private static final String STUDENT = "Student";

  @InjectMocks
  private ListCustomEventIqRequestHandler handler = new ListCustomEventIqRequestHandler();

  @Mock
  private CustomEventService customEventService;

  @Test
  public void handleIQRequest() throws Exception {
    // given
    ListCustomEventIqRequest iq = ListCustomEventIqRequest.builder()
        .student(STUDENT)
        .build();
    // when
    Mockito.when(customEventService.getCustomEventsByIq(iq)).thenReturn(CUSTOMEVENTS);
    // then
    
    ListCustomEventIqRequest handleIQRequest = (ListCustomEventIqRequest) handler.handleIQRequest(iq);
    
    Assert.assertEquals(CUSTOMEVENTS,handleIQRequest.getCustomEvents());
  }
}
