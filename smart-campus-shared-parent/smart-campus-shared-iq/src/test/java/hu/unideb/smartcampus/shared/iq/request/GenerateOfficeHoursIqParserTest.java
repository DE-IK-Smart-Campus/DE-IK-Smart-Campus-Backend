package hu.unideb.smartcampus.shared.iq.request;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.GenerateOfficeHoursIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIntervallIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.OfficeHourIqElement;

/**
 * Subjects IQ parser test.
 */
public class GenerateOfficeHoursIqParserTest extends AbstractParserTest {


  private static final Integer CREATED_HOURS = 20;

  private static final Long INSTRUCTOR_ID = 1L;

  private static final OfficeHourIntervallIqElement INTERVALL = OfficeHourIntervallIqElement
      .builder()
      .fromDate(20L)
      .toDate(30L)
      .build();

  private static final List<OfficeHourIqElement> OFFICE_HOURS =
      Arrays.asList(OfficeHourIqElement.builder()
          .day(DayOfWeek.MONDAY)
          .from("Monday 8:00")
          .to("Monday 10:00")
          .build(),
          OfficeHourIqElement.builder()
          .day(DayOfWeek.TUESDAY)
          .from("Tuesday 12:00")
          .to("Tuesday 14:00")
          .build());

  @Test
  public void testIqProvider() throws Exception {
    GenerateOfficeHoursIqRequest iq = GenerateOfficeHoursIqRequest
        .builder()
        .instructorId(INSTRUCTOR_ID)
        .intervall(INTERVALL)
        .officeHours(OFFICE_HOURS)
        .createdHours(CREATED_HOURS)
        .build();
    GenerateOfficeHoursIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(INSTRUCTOR_ID, parse.getInstructorId());
    Assert.assertEquals(INTERVALL, parse.getIntervall());
    Assert.assertEquals(CREATED_HOURS, parse.getCreatedHours());
    Assert.assertEquals(OFFICE_HOURS, parse.getOfficeHours());
  }

  @Override
  public String getElement() {
    return GenerateOfficeHoursIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new GenerateOfficeHoursIqProvider();
  }

}
