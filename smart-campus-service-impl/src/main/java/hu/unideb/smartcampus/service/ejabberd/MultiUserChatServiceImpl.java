package hu.unideb.smartcampus.service.ejabberd;

import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_DESTROY_ROOM_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_SUBSCRIBE_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.ResponseStatusValidator;
import hu.unideb.smartcampus.service.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.service.ejabberd.multiuserchat.request.CreateRoomRequest;
import hu.unideb.smartcampus.service.ejabberd.multiuserchat.request.DestroyRoomRequest;
import hu.unideb.smartcampus.service.ejabberd.multiuserchat.request.SubscribeRequest;
import hu.unideb.smartcampus.service.ejabberd.multiuserchat.request.UnsubscribeRequest;

/**
 * Multi user chat (MUC) service implementation.
 */
@Service
public class MultiUserChatServiceImpl implements MultiUserChatService {

  private static final String AT = "@";

  private static final Logger LOGGER = LoggerFactory.getLogger(MultiUserChatServiceImpl.class);

  /**
   * XMPP server host.
   */
  @Value("${smartcampus.xmpp.host}")
  private String host;

  /**
   * XMPP Multi user chat (MUC) service.
   */
  @Value("${smartcampus.xmpp.mucservice}")
  private String service;

  /**
   * XMPP nodes.
   */
  @Value("${smartcampus.xmpp.nodes}")
  private String nodes;

  @Autowired
  private ClientResponseProvider clientResponseProvider;

  @Autowired
  private ResponseStatusValidator statusValidator;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void createRoom(String roomName) {
    LOGGER.info("Creating new multi user chat (MUC) with name:{}", roomName);
    final CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
        .name(roomName)
        .host(host)
        .service(service)
        .build();

    final Response response = this.clientResponseProvider.sendPostRequest(MULTI_USER_CHAT_CREATE_ROOM_COMMAND,createRoomRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Multi user chat room with name {} created.", roomName);
    } else {
      LOGGER.info("Multi user chat room could not been created.", roomName);
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void subscribeToRoom(String user, String nick, String room) {
    LOGGER.info("Subscribing {} with {} nick to {}", user, nick, room);
    final SubscribeRequest subscribeRequest = SubscribeRequest.builder()
        .user(user + "/" + user)
        .nick(nick)
        .room(room + AT + service)
        .nodes(nodes)
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(MULTI_USER_CHAT_SUBSCRIBE_COMMAND,subscribeRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("User {} subscribed to room: {}", user, room);
    } else {
      LOGGER.info("User {} could not subscribe to room:{}", user, room);
    }
  }


  /**
   * {@inheritDoc}.
   */
  @Override
  public void unsubsribeFromRoom(String user, String room) {
    LOGGER.info("Unsubscribing user {} from room:{}", user, room);
    final UnsubscribeRequest unsubscribeRequest = UnsubscribeRequest.builder()
        .user(user)
        .room(room)
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND,unsubscribeRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("User {} unsubscribed from room: {}", user, room);
    } else {
      LOGGER.info("User {} could not unsubscribe from room:{}", user, room);
    }
  }


  /**
   * {@inheritDoc}.
   */
  @Override
  public void createRoomWithOptions(String roomName, Map<String, String> options) {
    LOGGER.info("Creating new multi user chat (MUC) with name:{}, with options.", roomName);
    final CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
        .name(roomName)
        .host(host)
        .service(service)
        .options(options)
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND,createRoomRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Multi user chat room with name {} created with given options.", roomName);
    } else {
      LOGGER.info("Multi user chat room could not been created.", roomName);
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void destroyRoom(String roomName) {
    LOGGER.info("Destroying room:{}", roomName);
    final DestroyRoomRequest destroyRoomRequest = DestroyRoomRequest.builder()
        .name(roomName)
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(MULTI_USER_CHAT_DESTROY_ROOM_COMMAND,destroyRoomRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Destroyed room:{}", roomName);
    } else {
      LOGGER.info("Coud not destroy room:{}", roomName);
    }
  }
}
