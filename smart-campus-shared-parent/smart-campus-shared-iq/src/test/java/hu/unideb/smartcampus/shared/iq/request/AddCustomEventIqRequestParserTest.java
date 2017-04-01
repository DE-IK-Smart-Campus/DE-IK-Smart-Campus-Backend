package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.AddCustomEventIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Calendar subjects IQ parser test.
 */
public class AddCustomEventIqRequestParserTest extends AbstractParserTest {

  private static final Long EVENT_ID = 1L;

  private static final String EVENT_REMINDER = "EventReminder";

  private static final Long EVENT_END = 2L;

  private static final Long EVENT_START = 1L;

  private static final String EVENT_REPEAT = "EventRepeat";

  private static final String EVENT_DESCRIPTION = "EventDescription";

  private static final String EVENT_PLACE = "EventPlace";

  private static final String EVENT_NAME = "EventName";

  private static final CustomEventIqElement CUSTOM_EVENT_IQ_ELEMENT = CustomEventIqElement.builder()
      .eventId(EVENT_ID)
      .eventName(EVENT_NAME)
      .eventPlace(EVENT_PLACE)
      .eventDescription(EVENT_DESCRIPTION)
      .eventRepeat(EVENT_REPEAT)
      .eventStart(EVENT_START)
      .eventEnd(EVENT_END)
      .reminder(EVENT_REMINDER)
      .build();

  private static final String STUDENT = "testUser";

  private static final List<CustomEventIqElement> CUSTOMEVENTS =
      Arrays.asList(CUSTOM_EVENT_IQ_ELEMENT);

  @Test
  public void testIqProvider() throws Exception {
    AddCustomEventIqRequest iq =
        AddCustomEventIqRequest.builder()
            .student(STUDENT)
            .customEvent(CUSTOM_EVENT_IQ_ELEMENT)
            .build();
    AddCustomEventIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(CUSTOM_EVENT_IQ_ELEMENT, parse.getCustomEvent());
  }

  @Override
  public String getElement() {
    return AddCustomEventIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new AddCustomEventIqProvider();
  }

}
