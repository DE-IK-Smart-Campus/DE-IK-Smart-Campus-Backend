package hu.unideb.smartcampus.service.api.xmpp.handler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.request.service.SignUpForConsultingDateRequestService;
import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import hu.unideb.smartcampus.shared.wrapper.SignUpForConsultingHourWrapper;

/**
 * Test for SignUpForConsultingDateRequestHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpForConsultingDateRequestHandlerTest {

  /**
   * User's username.
   */
  private static final String TEST_USER = "TestUser";

  /**
   * Consulting date id.
   */
  private static final long CONSULTING_DATE_ID = 1L;

  /**
   * User id.
   */
  private static final String USER_ID = "TestUser";

  /**
   * Example duration.
   */
  private static final String EXAMPLE_DURATION = "10 minutes";

  /**
   * Example reason.
   */
  private static final String EXAMPLE_REASON = "Discuss something important";

  /**
   * Example reqeust.
   */
  private static final SignUpForConsultingHourRequest EXAMPLE_REQUEST =
      SignUpForConsultingHourRequest.builder().consultingHourId(CONSULTING_DATE_ID).userId(USER_ID)
          .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_REQUEST)
          .duration(EXAMPLE_DURATION).reason(EXAMPLE_REASON).build();

  /**
   * OK status.
   */
  private static final String OK = "OK";

  /**
   * Wrapper to return with OK.
   */
  private static final SignUpForConsultingHourWrapper WRAPPER =
      SignUpForConsultingHourWrapper.builder().status(OK).build();

  /**
   * IQ request.
   */
  private static final SignUpForConsultingDateIqRequest IQ_REQUEST =
      SignUpForConsultingDateIqRequest.builder().duration(EXAMPLE_DURATION).reason(EXAMPLE_REASON)
          .consultingHourId(CONSULTING_DATE_ID).userId(TEST_USER).build();

  /**
   * Handler.
   */
  @InjectMocks
  private SignUpForConsultingDateRequestHandler handler =
      new SignUpForConsultingDateRequestHandler();

  /**
   * Request service.
   */
  @Mock
  private SignUpForConsultingDateRequestService requestService;

  /**
   * Test should return OK response message.
   */
  @Test
  public void testHandleIQRequestWithOKResponse() {
    // given

    // when
    Mockito.when(requestService.signUpByRequest(EXAMPLE_REQUEST)).thenReturn(WRAPPER);

    // then
    SignUpForConsultingDateIqRequest handleIQRequest =
        (SignUpForConsultingDateIqRequest) handler.handleIQRequest(IQ_REQUEST);

    Assert.assertEquals(OK, handleIQRequest.getResponseMessage());

  }
}
