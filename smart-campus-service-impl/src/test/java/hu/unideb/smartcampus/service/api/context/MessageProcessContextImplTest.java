package hu.unideb.smartcampus.service.api.context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.request.service.ExampleRequestServiceImpl;
import hu.unideb.smartcampus.service.api.request.service.RetrieveSubjectsRequestServiceImpl;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.ExampleRequest;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveSubjectsRequest;

/**
 * Test for {@link MessageProcessContextImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageProcessContextImplTest {

  /**
   * Classes of message processing context.
   */
  private List<MessageProcessingClass> classes =
      Arrays.asList(new RetrieveSubjectsRequestServiceImpl(), new ExampleRequestServiceImpl());

  /**
   * Context.
   */
  private MessageProcessContextImpl context = new MessageProcessContextImpl(classes);

  /**
   * Test getting message process services.
   */
  @Test
  public void getMessageServicesShouldReturnTheServices() {
    // given
    Map<Class<? extends BaseRequestType>, String> resultMap = initResultMap();

    // when
    Map<Class<? extends BaseRequestType>, String> messageServices = context.getMessageServices();

    // then
    Assert.assertEquals(resultMap, messageServices);

  }

  private Map<Class<? extends BaseRequestType>, String> initResultMap() {
    Map<Class<? extends BaseRequestType>, String> resultMap = new HashMap<>();
    resultMap.put(RetrieveSubjectsRequest.class, RetrieveSubjectsRequestServiceImpl.BEAN_NAME);
    resultMap.put(ExampleRequest.class, ExampleRequestServiceImpl.BEAN_NAME);
    return resultMap;
  }

}
