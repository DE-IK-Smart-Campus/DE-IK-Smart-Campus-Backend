package hu.unideb.smartcampus.service.api.request.service;

import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.wrapper.ExampleResponseWrapper;
import hu.unideb.smartcampus.shared.requestmessages.ExampleRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Example test for testing request services.
 *
 */
public class ExampleRequestServiceImplTest {


  /**
   * Example string.
   */
  private static final String EXAMPLE = "example";

  /**
   * Service.
   */
  private ExampleRequestServiceImpl service = new ExampleRequestServiceImpl();

  /**
   * Test get response.
   */
  @Test
  public void testGetResponse() {
    // given
    ExampleRequest request = ExampleRequest.builder().example(EXAMPLE)
        .messageType(RequestMessagesConstants.EXAMPLE_REQUEST).build();

    // when
    ExampleResponseWrapper response = service.getResponse(request);

    // then
    Assert.assertEquals(RequestMessagesConstants.EXAMPLE_RESPONSE, response.getMessageType());
    Assert.assertEquals(EXAMPLE, response.getExample());

  }

}
