package hu.unideb.smartcampus.service.api.calendar.helper.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import org.junit.Test;

import java.time.LocalDateTime;
import hu.unideb.smartcampus.service.api.calendar.helper.CalendarEventDateParseHelper;

/**
 * Test for {@link CalendarEventDateParseHelperImpl}.
 */
public class CalendarEventDateParseHelperImplTest {

  private static final String START_DATE_PROPERTY = "startDate";
  private static final String END_DATE_PROPERTY = "endDate";

  private static final int YEAR = 1970;
  private static final int MONTH = 01;
  private static final int DAY = 01;
  private static final int HOUR = 00;
  private static final int MINUTE = 00;
  private static final int SECOND = 00;

  private final LocalDateTime expectedLocaleDateTime = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND);

  private final CalendarEventDateParseHelper calendarEventDateParseHelper = new CalendarEventDateParseHelperImpl();

  @Test(expected = IllegalArgumentException.class)
  public void getStartDateTimeFromEventShouldThrowIllegalArgumentExceptionWhenParameterVEventIsNull() {
    // Given

    // When
    this.calendarEventDateParseHelper.getStartDateTimeFromEvent(null);

    // Then
  }

  @Test(expected = IllegalArgumentException.class)
  public void getStartDateTimeFromEventShouldThrowIllegalArgumentExceptionWhenParameterVEventFieldStartDateFieldDateIsNull() {
    // Given
    final Date startDate = null;

    // When
    this.calendarEventDateParseHelper.getStartDateTimeFromEvent(new VEvent(startDate, START_DATE_PROPERTY));

    // Then
  }

  @Test
  public void getStartDateTimeFromEventShouldReturnStartDateTime() {
    // Given
    final Date startDate = new Date(0L);

    // When
    final LocalDateTime result = this.calendarEventDateParseHelper.getStartDateTimeFromEvent(new VEvent(startDate, START_DATE_PROPERTY));

    // Then
    assertThat(result, notNullValue());
    assertThat(result, equalTo(expectedLocaleDateTime));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getEndDateTimeFromEventShouldThrowIllegalArgumentExceptionWhenParameterVEventIsNull() {
    // Given

    // When
    this.calendarEventDateParseHelper.getEndDateTimeFromEvent(null);

    // Then
  }

  @Test(expected = IllegalArgumentException.class)
  public void getEndDateTimeFromEventShouldThrowIllegalArgumentExceptionWhenParameterVEventFieldEndDateFieldDateIsNull() {
    // Given
    final Date endDate = null;
    final Date startDate = new Date();

    // When
    this.calendarEventDateParseHelper.getEndDateTimeFromEvent(new VEvent(startDate, endDate, END_DATE_PROPERTY));

    // Then
  }

  @Test
  public void getEndDateTimeFromEventShouldReturnEndDateTime() {
    // Given
    final Date startDate = new Date(0L);
    final Date endDate = new Date(0L);

    // When
    final LocalDateTime result = this.calendarEventDateParseHelper.getEndDateTimeFromEvent(new VEvent(startDate, endDate, END_DATE_PROPERTY));

    // Then
    assertThat(result, notNullValue());
    assertThat(result, equalTo(expectedLocaleDateTime));
  }
}
