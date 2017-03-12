package hu.unideb.smartcampus.service.api.impl;

import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.unideb.smartcampus.service.api.context.MessageProcessContext;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.BaseWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.ExampleResponseWrapper;
import hu.unideb.smartcampus.service.api.request.service.ExampleRequestServiceImpl;
import hu.unideb.smartcampus.shared.exception.ProcessMessageException;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ExampleRequest;


/**
 * Test for {@link MessageProcessingServiceImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageProcessingServiceImplTest {

  /**
   * Example message type.
   */
  private static final String EXAMPLE_MESSAGE_TYPE = "ExampleProcessMessage";

  /**
   * Example string.
   */
  private static final String EXAMPLE_STRING = "This is an example JSON hehe";

  /**
   * Example escaped JSON.
   */
  private static final String EXAMPLE_ESCAPED_JSON =
      "{\"messageType\":\"ExampleProcessMessage\",\"example\":\"This is an example JSON hehe\"}";

  /**
   * Example service implementation.
   */
  private ExampleRequestServiceImpl exampleService = new ExampleRequestServiceImpl();

  /**
   * Mock of object mapper.
   */
  private ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);

  /**
   * Mock of message process context.
   */
  @Mock
  private MessageProcessContext context;

  /**
   * Mock of application context.
   */
  @Mock
  private ApplicationContext appContext;

  /**
   * Message process service.
   */
  @InjectMocks
  private MessageProcessingServiceImpl messageProcessingService;

  /**
   * Test process messaging method with an example request.
   */
  @Test
  public void processMessageShouldProcessMessageAndReturnValidWrapper()
      throws JsonParseException, JsonMappingException, IOException, ProcessMessageException {
    // given

    // when
    given(context.getMessageServices()).willReturn(initResultMap());
    given(objectMapper.readValue(EXAMPLE_ESCAPED_JSON, BaseRequestType.class)).willReturn(
        ExampleRequest.builder().example(EXAMPLE_STRING).messageType(EXAMPLE_MESSAGE_TYPE).build());
    given(appContext.getBean(ExampleRequestServiceImpl.BEAN_NAME)).willReturn(exampleService);

    // then
    BaseWrapper processMessage = messageProcessingService.processMessage(EXAMPLE_ESCAPED_JSON);

    Assert.assertTrue(processMessage instanceof ExampleResponseWrapper);
    Assert.assertEquals(EXAMPLE_STRING, ((ExampleResponseWrapper) processMessage).getExample());
  }

  private Map<Class<? extends BaseRequestType>, String> initResultMap() {
    Map<Class<? extends BaseRequestType>, String> resultMap = new HashMap<>();
    resultMap.put(ExampleRequest.class, ExampleRequestServiceImpl.BEAN_NAME);
    return resultMap;
  }

}
