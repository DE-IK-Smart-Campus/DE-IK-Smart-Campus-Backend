package hu.unideb.smartcampus.shared.srg;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class to hold Ejabberd REST Endpoints for Shared Roster Group API.
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SharedRosterGroupConstants {
  /**
   * Endpoint URL for list all shared roster group.
   */
  public static final String SHARED_ROSTER_GROUP_LIST_COMMAND = "/srg_list";

  /**
   * Endpoint URL for create shared roster group.
   */
  public static final String SHARED_ROSTER_GROUP_CREATE_COMMAND = "/srg_create";

  /**
   * Endpoint URL for getting information about given shared roster group.
   */
  public static final String SHARED_ROSTER_GROUP_INFO_COMMAND = "/srg_get_info";

  /**
   * Endpoint URL for adding new user to shared roster group.
   */
  public static final String SHARED_ROSTER_GROUP_CREATE_USER_COMMAND = "/srg_user_add";

  /**
   * Endpoint URL for adding new user to shared roster group.
   */
  public static final String SHARED_ROSTER_GROUP_DELETE_USER_COMMAND = "/srg_user_del";
}
