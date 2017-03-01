package hu.unideb.smartcampus.webservice.api.ejabberd.impl;

import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.shared.enumeration.ConfigPropertyKey;
import hu.unideb.smartcampus.shared.muc.MultiUserChatConstants;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.CreateRoomRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.DestroyRoomRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.SubscribeRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.UnsubscribeRequest;
import hu.unideb.smartcampus.webservice.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.webservice.api.provider.PropertyProvider;
import hu.unideb.smartcampus.webservice.api.validator.impl.ResponseStatusValidatorImpl;


/**
 * MUC Service test.
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class MultiUserChatServiceImplTest {


  /**
   * Test user data.
   */
  private static final String TEST_USER = "user";

  /**
   * Test nick data.
   */
  private static final String TEST_NICK = "Test nick";

  /**
   * Test room data.
   */
  private static final String TEST_ROOM = "Test room";

  /**
   * OK response.
   */
  private static final Response OK_RESPONSE = Response.status(Status.OK).build();

  /**
   * BAD_REQUEST response.
   */
  private static final Response BAD_RESPONSE = Response.status(Status.BAD_REQUEST).build();

  /**
   * Test host and domain.
   */
  private static final String SMARTCAMPUS = "smartcampus";

  /**
   * Multi user chat service implementation.
   */
  @InjectMocks
  private MultiUserChatServiceImpl multiUserChatService;

  /**
   * Client response provider mock.
   */
  @Mock
  private ClientResponseProvider clientResponseProvider;

  /**
   * Property provider mock.
   */
  @Mock
  private PropertyProvider propertyProvider;

  /**
   * Response status validator spy.
   */
  @Spy
  private ResponseStatusValidatorImpl statusValidator;

  /**
   * Before each method.
   *
   * @throws Exception when something goes wrong.
   */
  @Before
  public void setUp() throws Exception {
    given(propertyProvider.getPropertyValue(ConfigPropertyKey.SMART_CAMPUS_XMPP_DOMAIN))
        .willReturn(SMARTCAMPUS);
  }

  /**
   * Test create room with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testCreateRoomOkResponse() {
    // given
    final CreateRoomRequest createRoomRequest = CreateRoomRequest.builder().name(TEST_ROOM).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_COMMAND, createRoomRequest))
            .willReturn(OK_RESPONSE);

    // when
    multiUserChatService.createRoom(TEST_ROOM);

    // then

  }

  /**
   * Test create room with BAD response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testCreateRoomBadResponse() {
    // given
    final CreateRoomRequest createRoomRequest = CreateRoomRequest.builder().name(TEST_ROOM).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_COMMAND, createRoomRequest))
            .willReturn(BAD_RESPONSE);

    // when
    multiUserChatService.createRoom(TEST_ROOM);

    // then

  }

  /**
   * Test subscribe to room with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testSubscribeToRoomOkResponse() {
    // given
    final SubscribeRequest subscribeRequest = SubscribeRequest.builder()
        .user(TEST_USER + "/" + TEST_USER).nick(TEST_NICK).room(TEST_ROOM + "@null").build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_SUBSCRIBE_COMMAND, subscribeRequest))
            .willReturn(OK_RESPONSE);

    // when
    multiUserChatService.subscribeToRoom(TEST_USER, TEST_NICK, TEST_ROOM);

    // then
  }

  /**
   * Test subscribe to room with BAD response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testSubscribeToRoomBadResponse() {
    // given
    final SubscribeRequest subscribeRequest = SubscribeRequest.builder()
        .user(TEST_USER + "/" + TEST_USER).nick(TEST_NICK).room(TEST_ROOM + "@null").build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_SUBSCRIBE_COMMAND, subscribeRequest))
            .willReturn(BAD_RESPONSE);

    // when
    multiUserChatService.subscribeToRoom(TEST_USER, TEST_NICK, TEST_ROOM);

    // then
  }

  /**
   * Test unsubscribe from room with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testUnsubsribeFromRoomOkResponse() {
    // given
    final UnsubscribeRequest unsubscribeRequest =
        UnsubscribeRequest.builder().user(TEST_USER).room(TEST_ROOM).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND, unsubscribeRequest))
            .willReturn(OK_RESPONSE);

    // when
    multiUserChatService.unsubsribeFromRoom(TEST_USER, TEST_ROOM);

    // then
  }

  /**
   * Test unsubscribe from room with BAD response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testUnsubsribeFromRoomBadResponse() {
    // given
    final UnsubscribeRequest unsubscribeRequest =
        UnsubscribeRequest.builder().user(TEST_USER).room(TEST_ROOM).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND, unsubscribeRequest))
            .willReturn(BAD_RESPONSE);

    // when
    multiUserChatService.unsubsribeFromRoom(TEST_USER, TEST_ROOM);

    // then
  }

  /**
   * Test create room with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testCreateRoomWithOptionsOkResponse() {
    // given
    Map<String, String> options = new HashMap<>();
    final CreateRoomRequest createRoomRequest =
        CreateRoomRequest.builder().name(TEST_ROOM).options(options).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND, createRoomRequest))
            .willReturn(OK_RESPONSE);

    // when
    multiUserChatService.createRoomWithOptions(TEST_ROOM, options);

    // then
  }

  /**
   * Test create room with BAD response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testCreateRoomWithOptionsBadResponse() {
    // given
    Map<String, String> options = new HashMap<>();
    final CreateRoomRequest createRoomRequest =
        CreateRoomRequest.builder().name(TEST_ROOM).options(options).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND, createRoomRequest))
            .willReturn(BAD_RESPONSE);

    // when
    multiUserChatService.createRoomWithOptions(TEST_ROOM, options);

    // then
  }

  /**
   * Test destroy room with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testDestroyRoomOkResponse() {
    // given
    final DestroyRoomRequest destroyRoomRequest =
        DestroyRoomRequest.builder().name(TEST_ROOM).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_DESTROY_ROOM_COMMAND, destroyRoomRequest))
            .willReturn(OK_RESPONSE);

    // when
    multiUserChatService.destroyRoom(TEST_ROOM);

    // then
  }

  /**
   * Test destroy room with BAD response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testDestroyRoomBadResponse() {
    // given
    final DestroyRoomRequest destroyRoomRequest =
        DestroyRoomRequest.builder().name(TEST_ROOM).build();
    given(clientResponseProvider.sendPostRequest(
        MultiUserChatConstants.MULTI_USER_CHAT_DESTROY_ROOM_COMMAND, destroyRoomRequest))
            .willReturn(BAD_RESPONSE);

    // when
    multiUserChatService.destroyRoom(TEST_ROOM);

    // then
  }

}
