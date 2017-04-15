package hu.unideb.smartcampus.service.api.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.SrgMucFacade;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.webservice.api.ejabberd.RoomGeneratorService;
import hu.unideb.smartcampus.webservice.api.ejabberd.SharedRosterService;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.muc.GetRoomOccupantsResponse;

/**
 * Service impl.
 */
@Service
public class SrgMucFacadeImpl implements SrgMucFacade {

  private static final String AT = "@";

  private static final String REPLACEMENT = "_";

  private static final Logger LOGGER = LoggerFactory.getLogger(SrgMucFacadeImpl.class);

  @Autowired
  private RoomGeneratorService roomGeneratorService;

  @Autowired
  private SharedRosterService sharedRosterService;

  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  private String domain;

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<String> generateMuc(List<SubjectEvent> events, String user) {
    return events.stream()
        .map(this::createMuc)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Async
  public void generateSrgAsync(List<SubjectEvent> events, String user) {
    events.stream().forEach(event -> createSharedRosterGroup(event, user));
  }

  private String createMuc(SubjectEvent event) {
    String roomName = getRoomName(event);
    String roomId = getRoomId(roomName);
    GetRoomOccupantsResponse response = roomGeneratorService.getRoomOccupants(roomId);
    if (!response.isFound()) {
      LOGGER.info("MUC does not exist with name:{}", roomName);
      roomGeneratorService.generateRoomWithOptions(roomId, roomName);
    } else {
      LOGGER.info("MUC exists with name:{}", roomName);
    }
    return getRoomNameById(roomId);
  }

  private String getRoomNameById(String roomId) {
    return roomId + AT + domain;
  }

  private void createSharedRosterGroup(SubjectEvent event, String user) {
    String groupName = getRoomName(event);
    String groupId = getRoomId(groupName);
    LOGGER.info("Creating new SRG with name:{} and id:{}", groupName, groupId);
    sharedRosterService.createNewGroup(groupId, groupName, groupName, Arrays.asList(groupId));
    sharedRosterService.addUserToGroup(user, groupId);
  }

  private String getRoomId(String name) {
    return StringUtils.stripAccents(replaceAllUnknownChars(name));
  }

  private String getRoomName(SubjectEvent event) {
    StringBuilder builder = new StringBuilder();
    builder.append(event.getSubjectDetails().getSubjectName()).append(" - ");
    builder.append(event.getSubjectDetails().getSubjectType()).append(" - ");
    builder.append(event.getCourseCode()).append(" - ");
    builder.append(returnRealString(event));
    return builder.toString();
  }

  private String returnRealString(SubjectEvent event) {
    String semester = event.getSubjectDetails().getSemester();
    return semester != null ? semester : "Unknown";
  }

  private String replaceAllUnknownChars(String name) {
    return name
        .toLowerCase()
        .replaceAll(" - ", REPLACEMENT)
        .replaceAll(" ", REPLACEMENT)
        .replaceAll("/", REPLACEMENT)
        .replaceAll("-", REPLACEMENT);
  }

}
