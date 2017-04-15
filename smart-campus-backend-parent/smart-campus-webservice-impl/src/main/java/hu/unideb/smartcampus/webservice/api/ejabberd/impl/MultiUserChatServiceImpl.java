package hu.unideb.smartcampus.webservice.api.ejabberd.impl;

import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_DESTROY_ROOM_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_ROOM_OCCUPANTS;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_SUBSCRIBE_COMMAND;
import static hu.unideb.smartcampus.shared.muc.MultiUserChatConstants.MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.webservice.api.ejabberd.MultiUserChatService;
import hu.unideb.smartcampus.webservice.api.ejabberd.domain.MucMember;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.CreateRoomRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.DestroyRoomRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.GetRoomOccupantsRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.GetRoomOccupantsResponse;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.SubscribeRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.UnsubscribeRequest;
import hu.unideb.smartcampus.webservice.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.webservice.api.validator.ResponseStatusValidator;

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
  @Resource(lookup = "java:global/smartcampus.xmpp.host")
  private String host;

  /**
   * XMPP Multi user chat (MUC) service.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.mucservice")
  private String service;

  /**
   * XMPP nodes.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.nodes")
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
    final CreateRoomRequest createRoomRequest =
        CreateRoomRequest.builder().name(roomName).host(host).service(service).build();

    final Response response = this.clientResponseProvider
        .sendPostRequest(MULTI_USER_CHAT_CREATE_ROOM_COMMAND, createRoomRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Multi user chat room with name {} created.", roomName);
    } else {
      LOGGER.info("Multi user chat room could not been created, status info:{}", response.getStatusInfo());
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void subscribeToRoom(String user, String nick, String room) {
    LOGGER.info("Subscribing {} with {} nick to {}", user, nick, room);
    final SubscribeRequest subscribeRequest = SubscribeRequest.builder().user(user + "/" + user)
        .nick(nick).room(room + AT + service).nodes(nodes).build();
    final Response response = this.clientResponseProvider
        .sendPostRequest(MULTI_USER_CHAT_SUBSCRIBE_COMMAND, subscribeRequest);
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
    final UnsubscribeRequest unsubscribeRequest =
        UnsubscribeRequest.builder().user(user).room(room).build();
    final Response response = this.clientResponseProvider
        .sendPostRequest(MULTI_USER_CHAT_UNSUBSCRIBE_COMMAND, unsubscribeRequest);
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
    final CreateRoomRequest createRoomRequest = CreateRoomRequest.builder().name(roomName)
        .host(host).service(service).options(options).build();
    final Response response = this.clientResponseProvider
        .sendPostRequest(MULTI_USER_CHAT_CREATE_ROOM_WITH_OPT_COMMAND, createRoomRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Multi user chat room with name {} created with given options.", roomName);
    } else {
      LOGGER.info("Multi user chat room could not been created, status info:{}", response.getStatusInfo());
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void destroyRoom(String roomName) {
    LOGGER.info("Destroying room:{}", roomName);
    final DestroyRoomRequest destroyRoomRequest =
        DestroyRoomRequest.builder().name(roomName).build();
    final Response response = this.clientResponseProvider
        .sendPostRequest(MULTI_USER_CHAT_DESTROY_ROOM_COMMAND, destroyRoomRequest);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Destroyed room:{}", roomName);
    } else {
      LOGGER.info("Coud not destroy room:{}", roomName);
    }
  }

  @Override
  public GetRoomOccupantsResponse getRoomOccupants(String room) {
    LOGGER.info("Get room occupants:{}", room);
    final GetRoomOccupantsRequest getRoomOccupants =
        GetRoomOccupantsRequest.builder()
            .name(room)
            .service(service)
            .build();
    List<MucMember> members = new ArrayList<>();
    boolean found = false;
    final Response response = this.clientResponseProvider
        .sendPostRequest(MULTI_USER_CHAT_ROOM_OCCUPANTS, getRoomOccupants);
    if (statusValidator.isOk(response)) {
      LOGGER.info("Room {} exists, getting occupants.", room);
      members = response.readEntity(new GenericType<List<MucMember>>() {});
      found = true;
    } else if (statusValidator.isBadRequest(response)) {
      LOGGER.warn("Room {} does not exists.", room);
    } else {
      LOGGER.info("Something went wrong, status info:", response.getStatusInfo());
    }
    return GetRoomOccupantsResponse.builder()
        .members(members)
        .found(found)
        .build();
  }
}
