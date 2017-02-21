package hu.unideb.smartcampus.service.ejabberd;


import java.util.List;
import java.util.Map;

/**
 * Service for managing shared roster service by Java side.
 *
 */
public interface SharedRosterService {

  /**
   * Endpoint URL for list all shared roster group.
   */
  String SHARED_ROSTER_GROUP_LIST_COMMAND = "/srg_list";

  /**
   * Endpoint URL for create shared roster group.
   */
  String SHARED_ROSTER_GROUP_CREATE_COMMAND = "/srg_create";

  /**
   * Endpoint URL for getting information about given shared roster group.
   */
  String SHARED_ROSTER_GROUP_INFO_COMMAND = "/srg_get_info";

  /**
   * Endpoint URL for adding new user to shared roster group.
   */
  String SHARED_ROSTER_GROUP_CREATE_USER_COMMAND = "/srg_user_add";

  /**
   * Endpoint URL for adding new user to shared roster group.
   */
  String SHARED_ROSTER_GROUP_DELETE_USER_COMMAND = "/srg_user_del";

  /**
   * Get the groups' list from the domain.
   *
   * @return groups list.
   */
  List<String> getGroupList();

  /**
   * Creating new Shared Roster Group.
   *
   * @param group group id.
   * @param groupName group name.
   * @param description description of the group.
   * @param displayedGroups displayed groups.
   */
  void createNewGroup(String group, String groupName, String description,
      List<String> displayedGroups);

  /**
   * Asking for group information.
   *
   * @param group group id.
   * @return Map which contains the group's details.
   */
  Map<String, String> getGroupInformation(String group);

  /**
   * Add user to given group.
   *
   * @param user user's account name withouth domain.
   * @param group group to add user.
   */
  void addUserToGroup(String user, String group);

  /**
   * Delete user from given group.
   *
   * @param user user's account name withouth domain.
   * @param group group to remove user.
   */
  void deleteUserFromGroup(String user, String group);
}
