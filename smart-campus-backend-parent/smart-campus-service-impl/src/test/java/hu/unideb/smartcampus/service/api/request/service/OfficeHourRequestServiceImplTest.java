package hu.unideb.smartcampus.service.api.request.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.OfficeHourResponseWrapper;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;
import hu.unideb.smartcampus.shared.requestmessages.CreateOfficeHoursRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Test for {@link OfficeHourRequestServiceImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
public class OfficeHourRequestServiceImplTest {

  /**
   * Instructor id.
   */
  private static final Long INSTRUCTOR_ID = 1L;

  /**
   * To date in string.
   */
  private static final String TO_IN_STRING = "14:00";

  /**
   * From date in string.
   */
  private static final String FROM_IN_STRING = "12:00";

  /**
   * Generated hours.
   */
  private static final Integer GENERATED_HOURS = 14;

  /**
   * Office hours.
   */
  private static final List<OfficeHour> OFFICEHOURS = Arrays.asList(
      OfficeHour.builder().day(DayOfWeek.MONDAY).from(FROM_IN_STRING).to(TO_IN_STRING).build());

  /**
   * Intervall.
   */
  private static final OfficeHourIntervall INTERVALL = OfficeHourIntervall.builder()
      .fromDate(Timestamp.valueOf(LocalDate.of(2017, 2, 20).atStartOfDay()))
      .toDate(Timestamp.valueOf(LocalDate.of(2017, 5, 26).atStartOfDay())).build();

  /**
   * Service.
   */
  @InjectMocks
  private OfficeHourRequestServiceImpl service;

  /**
   * Consulting hour service.
   */
  @Mock
  private ConsultingHourService consultingHourService;

  /**
   * Test get response.
   */
  @Test
  public void getResponse() {
    // given
    CreateOfficeHoursRequest request = CreateOfficeHoursRequest.builder()
        .instructorId(INSTRUCTOR_ID).intervall(INTERVALL).officeHours(OFFICEHOURS).build();

    // when
    Mockito.when(consultingHourService.generateOfficeHoursForInstructor(INSTRUCTOR_ID, OFFICEHOURS,
        INTERVALL)).thenReturn(GENERATED_HOURS);

    // then
    OfficeHourResponseWrapper response = service.getResponse(request);

    Assert.assertEquals(GENERATED_HOURS, response.getNumberOfGeneratedHours());
    Assert.assertEquals(RequestMessagesConstants.CREATE_CONSULTING_DATES_RESPONSE,
        response.getMessageType());
  }
}
