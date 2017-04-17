package hu.unideb.smartcampus.service.api.impl;

import java.time.LocalDate;
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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.persistence.repository.CustomEventRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.converter.toentity.CustomEventIqToCustomEventEntity;
import hu.unideb.smartcampus.service.api.converter.toiq.CustomEventEntityToCustomEventIq;
import hu.unideb.smartcampus.service.api.util.DateUtil;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Test custom event service impl.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomEventServiceImplTest {

  private static final String GUID = "GUID";

  private static final String EVENT_REMINDER = "EventReminder";

  private static final LocalDateTime EVENT_START_LOCALDATETIME = LocalDateTime.now();

  private static final LocalDateTime EVENT_END_LOCALDATETIME =
      LocalDateTime.now().plus(1, ChronoUnit.HOURS);

  private static final LocalDate EVENT_WHEN_LOCALDATE = LocalDate.now();

  private static final Long EVENT_START =
      EVENT_START_LOCALDATETIME.toEpochSecond(ZoneOffset.ofHours(2));

  private static final Long EVENT_WHEN =
      EVENT_WHEN_LOCALDATE.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(2));

  private static final Long EVENT_END =
      EVENT_END_LOCALDATETIME.toEpochSecond(ZoneOffset.ofHours(2));

  private static final String EVENT_REPEAT = "EventRepeat";

  private static final String EVENT_DESCRIPTION = "EventDescription";

  private static final String EVENT_PLACE = "EventPlace";

  private static final String EVENT_NAME = "EventName";

  private static final CustomEventIqElement CUSTOM_EVENT_IQ_ELEMENT = CustomEventIqElement.builder()
      .guid(GUID)
      .eventName(EVENT_NAME)
      .eventPlace(EVENT_PLACE)
      .eventDescription(EVENT_DESCRIPTION)
      .eventWhen(EVENT_WHEN)
      .eventRepeat(EVENT_REPEAT)
      .reminder(EVENT_REMINDER)
      .eventStart(EVENT_START)
      .eventEnd(EVENT_END)
      .build();

  private static final String STUDENT = "testUser";

  private static final List<CustomEventIqElement> CUSTOMEVENTS =
      Arrays.asList(CUSTOM_EVENT_IQ_ELEMENT);


  private static final List<CustomEventEntity> EVENT_ENTITY_LIST =
      Arrays.asList(CustomEventEntity.builder()
          .guid(GUID)
          .eventName(EVENT_NAME)
          .eventPlace(EVENT_PLACE)
          .eventDescription(EVENT_DESCRIPTION)
          .eventRepeat(EVENT_REPEAT)
          .eventWhen(EVENT_WHEN_LOCALDATE)
          .eventStart(EVENT_START_LOCALDATETIME)
          .eventEnd(EVENT_END_LOCALDATETIME)
          .reminder(EVENT_REMINDER)
          .build());

  private static final ListCustomEventIqRequest LIST_IQ_REQUEST =
      ListCustomEventIqRequest.builder()
          .student(STUDENT)
          .build();

  @InjectMocks
  private CustomEventServiceImpl service;

  @Mock
  private UserRepository userRepository;

  @Mock
  private CustomEventRepository customEventRepository;

  @Spy
  private DateUtil dateUtil = new DateUtil();

  @Spy
  private CustomEventEntityToCustomEventIq entityToIqConverter =
      new CustomEventEntityToCustomEventIq(dateUtil);

  @Spy
  private CustomEventIqToCustomEventEntity iqToEntityConverter =
      new CustomEventIqToCustomEventEntity(dateUtil);

  /**
   * Test get custom events by IQ.
   */
  @Test
  public void getCustomEventsByIq() {
    // given

    // when
    Mockito.when(customEventRepository.getEventsByUsername(STUDENT))
        .thenReturn(EVENT_ENTITY_LIST);
    // then
    List<CustomEventIqElement> customEventsByIq = service.getCustomEventsByIq(LIST_IQ_REQUEST);

    Assert.assertEquals(customEventsByIq, CUSTOMEVENTS);
  }

  /**
   * Test add custom event by IQ.
   */
  @Test
  public void addCustomEntityByIq() {
    // given

    // when

    // then
  }

  /**
   * Test delete custom event by IQ.
   */
  @Test
  public void deleteCustomEntityByIq() {
    // given

    // when

    // then
  }

}
