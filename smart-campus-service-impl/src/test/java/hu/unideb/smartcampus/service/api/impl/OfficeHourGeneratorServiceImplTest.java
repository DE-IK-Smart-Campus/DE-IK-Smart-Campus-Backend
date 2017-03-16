package hu.unideb.smartcampus.service.api.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.util.OfficeHour;
import hu.unideb.smartcampus.service.api.domain.util.OfficeHourIntervall;

/**
 * Testing office hour generator service.
 *
 */
@RunWith(Parameterized.class)
public class OfficeHourGeneratorServiceImplTest {

  /**
   * Service.
   */
  private OfficeHourGeneratorServiceImpl service = new OfficeHourGeneratorServiceImpl();

  /**
   * Parametrized intervall.
   */
  private OfficeHourIntervall intervall;

  /**
   * Parametrized officeHours.
   */
  private List<OfficeHour> officeHours;

  /**
   * Expected size.
   */
  private Integer expectedSize;

  /**
   * Constructs a test instance.
   */
  public OfficeHourGeneratorServiceImplTest(OfficeHourIntervall intervall,
      List<OfficeHour> officeHours, Integer expectedSize) {
    this.intervall = intervall;
    this.officeHours = officeHours;
    this.expectedSize = expectedSize;
  }

  /**
   * Parameters.
   */
  @Parameters(
      name = "{index}: generating office hours in intervall:{0}\n Office hours:{1}\n expected generated days:{2}")
  public static Collection<Object[]> createParameters() {
    // @formatter:off
    Object[][] testArray = new Object[][]{
            {buildIntervall(2017, 3, 1,2017, 4, 10), Arrays.asList(OfficeHour.builder().day(DayOfWeek.FRIDAY).from("13:15").to("14:15").build()), 6},
            {buildIntervall(2017, 4, 11,2017, 4, 10), Arrays.asList(OfficeHour.builder().day(DayOfWeek.FRIDAY).from("13:15").to("14:15").build()), 0},
            {buildIntervall(2017, 2, 20,2017, 5, 26),
             Arrays.asList(
                 OfficeHour.builder().day(DayOfWeek.TUESDAY).from("11:00").to("12:00").build(),
                 OfficeHour.builder().day(DayOfWeek.WEDNESDAY).from("15:30").to("16:30").build()),
             28}
    };
    // @formatter:on
    return Arrays.asList(testArray);
  }

  /**
   * Intervall builder.
   */
  private static OfficeHourIntervall buildIntervall(int fromYear, int fromMonth, int fromDay,
      int toYear, int toMonth, int toDay) {
    return OfficeHourIntervall.builder().fromDate(LocalDate.of(fromYear, fromMonth, fromDay))
        .toDate(LocalDate.of(toYear, toMonth, toDay)).build();
  }

  /**
   * Test generate office hours in given intervall.
   */
  @Test
  public void testGenerateOfficeHourWithinGivenIntervall() {
    List<ConsultingDate> generateOfficeHoursInIntervall =
        service.generateOfficeHoursInIntervall(intervall, officeHours);
    Assert.assertEquals(expectedSize.intValue(), generateOfficeHoursInIntervall.size());
  }

}
