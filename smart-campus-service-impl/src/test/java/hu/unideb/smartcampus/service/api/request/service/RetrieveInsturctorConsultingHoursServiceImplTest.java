package hu.unideb.smartcampus.service.api.request.service;

import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_FRIDAY;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_FRIDAY_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_MONDAY;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.CONSULTING_DATE_MONDAY_ID;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.FRIDAY_END_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.FRIDAY_START_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.MONDAY_END_DATE;
import static hu.unideb.smartcampus.shared.test.property.InstructorTestProperty.MONDAY_START_DATE;

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
import hu.unideb.smartcampus.persistence.util.FromToDateUtil;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.InstructorConsultingHoursWrapper;
import hu.unideb.smartcampus.service.api.request.service.RetrieveInstructorsConsultingHoursRequestServiceImpl;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveInstructorConsultingHours;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Test for {@link RetrieveInstructorsConsultingHoursRequestServiceImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
public class RetrieveInsturctorConsultingHoursServiceImplTest {


  /**
   * Instructor id.
   */
  private static final long INSTRUCTOR_ID = 1L;

  /**
   * Service impl.
   */
  @InjectMocks
  private RetrieveInstructorsConsultingHoursRequestServiceImpl service =
      new RetrieveInstructorsConsultingHoursRequestServiceImpl();

  /**
   * Instructor repository.
   */
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
    return ConsultingDateEntity.builder().id(CONSULTING_DATE_MONDAY_ID).date(CONSULTING_DATE_MONDAY)
        .fromToDate(getMonday()).build();
  }

  private FromToDateEmbeddedEntity getMonday() {
    return FromToDateUtil.createEntity(MONDAY_START_DATE, MONDAY_END_DATE);
  }

  private ConsultingDateEntity createFridayConsultingDate() {
    return ConsultingDateEntity.builder().id(CONSULTING_DATE_FRIDAY_ID).date(CONSULTING_DATE_FRIDAY)
        .fromToDate(getFriday()).build();
  }

  private FromToDateEmbeddedEntity getFriday() {
    return FromToDateUtil.createEntity(FRIDAY_START_DATE, FRIDAY_END_DATE);
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
