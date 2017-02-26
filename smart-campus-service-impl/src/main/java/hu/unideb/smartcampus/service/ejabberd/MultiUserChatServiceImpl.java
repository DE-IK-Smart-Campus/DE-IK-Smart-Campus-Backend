package hu.unideb.smartcampus.service.ejabberd;

import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_DESTROY_ROOM_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_SUBSCRIBE_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.HttpStatusValidator;
import hu.unideb.smartcampus.service.api.provider.ClientProvider;
import hu.unideb.smartcampus.service.ejabberd.muc.request.CreateRoomRequest;
import hu.unideb.smartcampus.service.ejabberd.muc.request.DestroyRoomRequest;
import hu.unideb.smartcampus.service.ejabberd.muc.request.SubscribeRequest;
import hu.unideb.smartcampus.service.ejabberd.muc.request.UnsubscribeRequest;

/**
 * Multi user chat (MUC) service implementation.
 *
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
  private ClientProvider clientProvider;

  @Autowired
  private HttpStatusValidator statusValidator;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void createRoom(String roomName) {
    LOGGER.info("Creating new multi user chat (MUC) with name:{}", roomName);
    WebTarget client = clientProvider.createClientByUrl(MULTI_USER_CHAT_CREATE_ROOM_COMMAND);
    Entity<CreateRoomRequest> entity = Entity.entity(
        CreateRoomRequest.builder().name(roomName).host(host).service(service).build(),
        MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (statusValidator.isOk(post)) {
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
    WebTarget client = clientProvider.createClientByUrl(MULTI_USER_CHAT_SUBSCRIBE_COMMAND);
    Entity<SubscribeRequest> entity =
        Entity.entity(SubscribeRequest.builder().user(user + "/" + user).nick(nick)
            .room(room + AT + service).nodes(nodes).build(), MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (statusValidator.isOk(post)) {
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
    WebTarget client = clientProvider.createClientByUrl(MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND);
    Entity<UnsubscribeRequest> entity = Entity.entity(
        UnsubscribeRequest.builder().user(user).room(room).build(), MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (statusValidator.isOk(post)) {
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
    WebTarget client =
        clientProvider.createClientByUrl(MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND);
    Entity<CreateRoomRequest> entity = Entity.entity(CreateRoomRequest.builder().name(roomName)
        .host(host).service(service).options(options).build(), MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (statusValidator.isOk(post)) {
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
    WebTarget client = clientProvider.createClientByUrl(MULTI_USER_CHAT_DESTROY_ROOM_COMMAND);
    Entity<DestroyRoomRequest> entity = Entity
        .entity(DestroyRoomRequest.builder().name(roomName).build(), MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (statusValidator.isOk(post)) {
      LOGGER.info("Destroyed room:{}", roomName);
    } else {
      LOGGER.info("Coud not destroy room:{}", roomName);
    }
  }
}
