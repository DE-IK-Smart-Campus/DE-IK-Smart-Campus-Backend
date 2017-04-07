package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.jivesoftware.smack.packet.IQ;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.request.service.RetrieveInstructorsConsultingDatesRequestService;
import hu.unideb.smartcampus.service.api.xmpp.handler.consulting.InstructorConsultingDateIqRequestHandler;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;
import hu.unideb.smartcampus.shared.wrapper.inner.ConsultingDateWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.FromToDateWrapper;

/**
 * Test SubjectRequestIqRequestHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class InstructorConsultingDateIqRequestHandlerTest {

  /**
   * Instructor name.
   */
  private static final String INSTRUCTOR_NAME = "John";

  /**
   * Instructor ID.
   */
  private static final Long INSTRUCTOR_ID = 1L;


  /**
   * IQ request.
   */
  private static final IQ IQREQUEST =
      InstructorConsultingDatesIqRequest.builder().instructorId(INSTRUCTOR_ID.toString()).build();

  /**
   * Consulting dates.
   */
  private static final List<ConsultingDateWrapper> CONSULTING_DATES =
      Arrays.asList(ConsultingDateWrapper.builder().consultingHourId(2L)
          .fromToDates(FromToDateWrapper.builder().from(1L).to(2L).build()).reservedSum(0).build());

  /**
   * Expected list.
   */
  private static final List<ConsultingDateIqElement> EXPECTED =
      Arrays.asList(ConsultingDateIqElement.builder().consultingHourId(2L)
          .fromToDates(FromToDateIqElement.builder().from(1L).to(2L).build()).reservedSum(0)
          .build());

  /**
   * Handler.
   */
  @InjectMocks
  private InstructorConsultingDateIqRequestHandler handler =
      new InstructorConsultingDateIqRequestHandler();

  /**
   * Request service.
   */
  @Mock
  private RetrieveInstructorsConsultingDatesRequestService requestService;

  @Test
  public void testHandleIQRequest() {
    // given

    // when
    Mockito.when(requestService.getConsultingDatesByInstructorId(INSTRUCTOR_ID))
        .thenReturn(CONSULTING_DATES);

    // then
    InstructorConsultingDatesIqRequest handleIQRequest =
        (InstructorConsultingDatesIqRequest) handler.handleIQRequest(IQREQUEST);

    Assert.assertThat(EXPECTED, Matchers.is(handleIQRequest.getConsultingDates()));
  }

}
