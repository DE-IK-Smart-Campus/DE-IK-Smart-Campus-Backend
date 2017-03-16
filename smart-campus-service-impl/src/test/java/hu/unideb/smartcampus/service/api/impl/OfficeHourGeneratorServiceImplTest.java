package hu.unideb.smartcampus.service.api.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.util.OfficeHour;
import hu.unideb.smartcampus.service.api.domain.util.OfficeHourIntervall;

@RunWith(MockitoJUnitRunner.class)
public class OfficeHourGeneratorServiceImplTest {

  private OfficeHourGeneratorServiceImpl service = new OfficeHourGeneratorServiceImpl();

  @Test
  public void testGenerateOfficeHourWithinGivenIntervall() {
    OfficeHourIntervall intervall = OfficeHourIntervall.builder().fromDate(LocalDate.of(2017, 3, 1))
        .toDate(LocalDate.of(2017, 4, 10)).build();
    List<OfficeHour> officeHours =
        Arrays.asList(OfficeHour.builder().day(DayOfWeek.FRIDAY).from("13:15").to("14:15").build());
    List<ConsultingDate> generateOfficeHoursInIntervall =
        service.generateOfficeHoursInIntervall(intervall, officeHours);
  }

}
