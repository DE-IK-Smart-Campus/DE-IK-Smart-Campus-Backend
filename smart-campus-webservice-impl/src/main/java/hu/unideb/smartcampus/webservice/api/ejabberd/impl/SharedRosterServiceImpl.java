package hu.unideb.smartcampus.webservice.api.ejabberd.impl;

import static hu.unideb.smartcampus.shared.enumeration.ConfigPropertyKey.SMART_CAMPUS_XMPP_DOMAIN;
import static hu.unideb.smartcampus.shared.srg.SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_COMMAND;
import static hu.unideb.smartcampus.shared.srg.SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_USER_COMMAND;
import static hu.unideb.smartcampus.shared.srg.SharedRosterGroupConstants.SHARED_ROSTER_GROUP_DELETE_USER_COMMAND;
import static hu.unideb.smartcampus.shared.srg.SharedRosterGroupConstants.SHARED_ROSTER_GROUP_INFO_COMMAND;
import static hu.unideb.smartcampus.shared.srg.SharedRosterGroupConstants.SHARED_ROSTER_GROUP_LIST_COMMAND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import hu.unideb.smartcampus.webservice.api.ejabberd.SharedRosterService;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.AddUserRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.CreateGroupRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.DeleteUseRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.GroupRequest;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster.InformationRequest;
import hu.unideb.smartcampus.webservice.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.webservice.api.provider.PropertyProvider;
import hu.unideb.smartcampus.webservice.api.validator.ResponseStatusValidator;


/**
 * Shared Roster Service Implementation which implements {@link SharedRosterService}.
 *
 * <p>
 * It handles shared roster groups (srg) via REST API which is provided by <b>Ejabberd</b>.
 * </p>
 */
@Service
public class SharedRosterServiceImpl implements SharedRosterService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SharedRosterServiceImpl.class);
  private static final GenericType<Map<String, String>> MAP_GENERIC_TYPE = new GenericType<Map<String, String>>() {
  };
  private static final GenericType<List<String>> LIST_GENERIC_TYPE = new GenericType<List<String>>() {
  };

  @Autowired
  private PropertyProvider propertyProvider;

  @Autowired
  private ResponseStatusValidator statusValidator;

  @Autowired
  private ClientResponseProvider clientResponseProvider;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void addUserToGroup(String user, String group) {
    LOGGER.info("Add user {} to group {}", user, group);

    final AddUserRequest addUserRequest = AddUserRequest.builder()
        .host(this.getXmppDomainPropertyValue())
        .group(group)
        .user(user)
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(SHARED_ROSTER_GROUP_CREATE_USER_COMMAND, addUserRequest);

    if (statusValidator.isOk(response)) {
      LOGGER.info("{} user added to group {}", user, group);
    } else {
      LOGGER.info("Adding user to group failed... Try again later.");
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void createNewGroup(String group, String groupName, String description,
                             List<String> displayedGroups) {
    LOGGER.info("Creating group called {}.", group);

    final CreateGroupRequest createGroupRequest = CreateGroupRequest.builder()
        .host(this.getXmppDomainPropertyValue())
        .group(group)
        .description(description)
        .name(groupName)
        .display(this.getDisplayedGroups(displayedGroups))
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(SHARED_ROSTER_GROUP_CREATE_COMMAND, createGroupRequest);

    if (statusValidator.isOk(response)) {
      LOGGER.info("{} created.", group);
    } else {
      LOGGER.info("Creating group failed... Try again later.");
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void deleteUserFromGroup(String user, String group) {
    LOGGER.info("Deleting user {} from group called {}.", user, group);

    final DeleteUseRequest deleteUseRequest = DeleteUseRequest.builder()
        .host(this.getXmppDomainPropertyValue())
        .user(user)
        .group(group)
        .build();
    final Response response = this.clientResponseProvider.sendPostRequest(SHARED_ROSTER_GROUP_DELETE_USER_COMMAND, deleteUseRequest);

    if (statusValidator.isOk(response)) {
      LOGGER.info("{} deleted from group {}.", user, group);
    } else {
      LOGGER.info("Deleting user failed... Try again later.");
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Map<String, String> getGroupInformation(String group) {
    LOGGER.info("Getting group information, {}.", group);

    final InformationRequest informationRequest = InformationRequest.builder()
        .host(this.getXmppDomainPropertyValue())
        .group(group)
        .build();
    return this.clientResponseProvider.sendPostRequest(SHARED_ROSTER_GROUP_INFO_COMMAND, informationRequest)
        .readEntity(MAP_GENERIC_TYPE);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<String> getGroupList() {
    LOGGER.info("Getting group list.");

    final GroupRequest groupRequest = GroupRequest.builder()
        .host(this.getXmppDomainPropertyValue())
        .build();
    return this.clientResponseProvider.sendPostRequest(SHARED_ROSTER_GROUP_LIST_COMMAND, groupRequest)
        .readEntity(LIST_GENERIC_TYPE);
  }

  private String getDisplayedGroups(List<String> displayedGroups) {
    StringBuilder builder = new StringBuilder();
    displayedGroups.stream().forEach(p -> builder.append(p).append("\n"));
    return builder.toString();
  }

  private String getXmppDomainPropertyValue() {
    return this.propertyProvider.getPropertyValue(SMART_CAMPUS_XMPP_DOMAIN);
  }

}
