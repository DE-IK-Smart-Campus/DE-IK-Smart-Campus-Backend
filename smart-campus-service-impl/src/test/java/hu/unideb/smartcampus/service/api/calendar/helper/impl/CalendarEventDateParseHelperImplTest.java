package hu.unideb.smartcampus.service.api.calendar.helper.impl;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import org.junit.Test;

import hu.unideb.smartcampus.service.api.calendar.helper.CalendarEventDateParseHelper;

/**
 * Test for {@link CalendarEventDateParseHelperImpl}.
 */
public class CalendarEventDateParseHelperImplTest {

  private static final String START_DATE_PROPERTY = "startDate";
  private static final String END_DATE_PROPERTY = "endDate";

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
}
