package hu.unideb.smartcampus.service.ejabberd;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.ejabberd.request.AddUserRequest;
import hu.unideb.smartcampus.service.ejabberd.request.CreateGroupRequest;
import hu.unideb.smartcampus.service.ejabberd.request.DeleteUseRequest;
import hu.unideb.smartcampus.service.ejabberd.request.GroupRequest;
import hu.unideb.smartcampus.service.ejabberd.request.InformationRequest;


/**
 * Shared Roster Service Implementation which implements {@link SharedRosterService}.
 *
 * <p>
 * It handles shared roster groups (srg) via REST API which is provided by <b>Ejabberd</b>.
 * </p>
 */
@Service
public class SharedRosterServiceImpl extends EjabberdService implements SharedRosterService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SharedRosterServiceImpl.class);

  /**
   * {@inheritDoc}.
   */
  @Override
  public void addUserToGroup(String user, String group) {
    LOGGER.info("Add user {} to group {}", user, group);
    WebTarget client = createClientByUrl(SHARED_ROSTER_GROUP_CREATE_USER_COMMAND);
    Entity<AddUserRequest> entity =
        Entity.entity(AddUserRequest.builder().host(DOMAIN).group(group).user(user).build(),
            MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (isSuccessful(post)) {
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
    WebTarget target = createClientByUrl(SHARED_ROSTER_GROUP_CREATE_COMMAND);
    Entity<CreateGroupRequest> entity = Entity.entity(
        CreateGroupRequest.builder().host(DOMAIN).group(group).description(description)
            .name(groupName).display(getDisplayedGroups(displayedGroups)).build(),
        MediaType.APPLICATION_JSON);
    Response post = target.request().post(entity);
    if (isSuccessful(post)) {
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
    WebTarget client = createClientByUrl(SHARED_ROSTER_GROUP_DELETE_USER_COMMAND);
    Entity<DeleteUseRequest> entity =
        Entity.entity(DeleteUseRequest.builder().host(DOMAIN).group(group).user(user).build(),
            MediaType.APPLICATION_JSON);
    Response post = client.request().post(entity);
    if (isSuccessful(post)) {
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
    WebTarget client = createClientByUrl(SHARED_ROSTER_GROUP_INFO_COMMAND);
    Entity<InformationRequest> entity = Entity.entity(
        InformationRequest.builder().host(DOMAIN).group(group).build(), MediaType.APPLICATION_JSON);
    return client.request().post(entity).readEntity(new GenericType<Map<String, String>>() {});
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<String> getGroupList() {
    LOGGER.info("Getting group list.");
    WebTarget target = createClientByUrl(SHARED_ROSTER_GROUP_LIST_COMMAND);
    Entity<GroupRequest> entity =
        Entity.entity(new GroupRequest(DOMAIN), MediaType.APPLICATION_JSON);
    return target.request().post(entity).readEntity(new GenericType<List<String>>() {});
  }

  private WebTarget createClientByUrl(String url) {
    Client client = ClientBuilder.newClient();
    return client.target(HOST + url);
  }

  private String getDisplayedGroups(List<String> displayedGroups) {
    StringBuilder builder = new StringBuilder();
    displayedGroups.stream().forEach(p -> builder.append(p).append("\n"));
    return builder.toString();
  }

  private boolean isSuccessful(Response post) {
    return post.getStatus() == 200;
  }

}
