package hu.unideb.smartcampus.service.api.requestprocess;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.InstructorConsultingHoursWrapper;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveInstructorConsultingHours;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveInsturctorConsultingHoursServiceImplTest {


  private static final long INSTRUCTOR_ID = 1L;

  @InjectMocks
  private RetrieveInsturctorConsultingHoursServiceImpl service =
      new RetrieveInsturctorConsultingHoursServiceImpl();

  @Mock
  private InstructorRepository instructorRepositoy;

  /**
   * Friday consulting date entity.
   */
  private ConsultingDateEntity fridayConsultingDate = createFridayConsultingDate();


  /**
   * Friday consulting date entity.
   */
  private ConsultingDateEntity mondayConsultingDate = createMondayConsultingDate();

  private ConsultingDateEntity createMondayConsultingDate() {
    return ConsultingDateEntity.builder().id(2L).date("Monday 08-10").fromToDate(getMonday()).sum(2)
        .build();
  }

  private FromToDateEmbeddedEntity getMonday() {
    // 2017-03-07 14:00:00
    Calendar from = Calendar.getInstance();
    from.set(2017, 2, 6, 8, 0, 0);

    // 2017-03-07 16:00:00
    Calendar to = Calendar.getInstance();
    to.set(2017, 2, 6, 10, 0, 0);

    Timestamp fromDate = new Timestamp(from.getTime().getTime());
    fromDate.setNanos(0);
    Timestamp toDate = new Timestamp(to.getTime().getTime());
    toDate.setNanos(0);

    return FromToDateEmbeddedEntity.builder().fromDate(fromDate).toDate(toDate).build();
  }


  private ConsultingDateEntity createFridayConsultingDate() {
    return ConsultingDateEntity.builder().id(1L).date("Friday 14-16").fromToDate(getFriday()).sum(3)
        .build();
  }

  private FromToDateEmbeddedEntity getFriday() {
    // 2017-03-07 14:00:00
    Calendar from = Calendar.getInstance();
    from.set(2017, 2, 10, 14, 0, 0);

    // 2017-03-07 16:00:00
    Calendar to = Calendar.getInstance();
    to.set(2017, 2, 10, 16, 0, 0);

    Timestamp fromDate = new Timestamp(from.getTime().getTime());
    fromDate.setNanos(0);
    Timestamp toDate = new Timestamp(to.getTime().getTime());
    toDate.setNanos(0);

    return FromToDateEmbeddedEntity.builder().fromDate(fromDate).toDate(toDate).build();
  }

  /**
   * Test get response.
   */
  @Test
  public void getResponseShouldReturnValidResponse() {
    // given
    RetrieveInstructorConsultingHours request =
        RetrieveInstructorConsultingHours.builder().instructorId(INSTRUCTOR_ID)
            .messageType(RequestMessagesConstants.RETRIEVE_CONSULTING_HOURS).build();

    Set<ConsultingDateEntity> consultingDates =
        Sets.newSet(fridayConsultingDate, mondayConsultingDate);

    Calendar from = Calendar.getInstance();
    Calendar to = Calendar.getInstance();
    to.add(Calendar.WEEK_OF_YEAR, 1);

    // when
    Mockito.when(instructorRepositoy.getInstructorConsultingDatesByIdAndGivenDate(INSTRUCTOR_ID,
        from.getTime(), to.getTime())).thenReturn(consultingDates);


    // then
    InstructorConsultingHoursWrapper response = service.getResponse(request);
    Assert.assertEquals(RequestMessagesConstants.RETRIEVE_CONSULTING_HOURS_RESPONSE,
        response.getMessageType());

  }

}
