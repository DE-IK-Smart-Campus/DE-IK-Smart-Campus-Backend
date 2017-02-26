package hu.unideb.smartcampus.service.ejabberd;


import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.impl.ResponseStatusValidatorImpl;
import hu.unideb.smartcampus.service.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.service.api.provider.PropertyProvider;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.AddUserRequest;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.CreateGroupRequest;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.DeleteUseRequest;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.GroupRequest;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.InformationRequest;
import hu.unideb.smartcampus.shared.enumeration.ConfigPropertyKey;
import hu.unideb.smartcampus.shared.srg.SharedRosterGroupConstants;

/**
 * Test for SRG service.
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class SharedRosterServiceImplTest {

  /**
   * Endline constant.
   */
  private static final String ENDLINE = "\n";

  /**
   * Test Group Two test data.
   */
  private static final String TEST_GROUP_TWO = "Test Group Two";

  /**
   * Test Group One test data.
   */
  private static final String TEST_GROUP_ONE = "Test Group One";

  /**
   * Test data in list.
   */
  private static final List<String> GROUP_LIST = Arrays.asList(TEST_GROUP_ONE, TEST_GROUP_TWO);

  /**
   * OK response.
   */
  private static final Response OK_RESPONSE = Response.status(Status.OK).build();

  /**
   * BAD_REQUEST response.
   */
  private static final Response BAD_RESPONSE = Response.status(Status.BAD_REQUEST).build();

  /**
   * Test value for map.
   */
  private static final String TEST_VALUE = "Test value";

  /**
   * Test key for map.
   */
  private static final String TEST_KEY = "Test key";

  /**
   * Displayed Groups in room list.
   */
  private static final String DISPLAYED_GROUPS = "displayedGroups";

  /**
   * Displayed groups with endline character.
   */
  private static final String DISPLAYED_GROUPS_PARAM = DISPLAYED_GROUPS + ENDLINE;

  /**
   * Test description.
   */
  private static final String TEST_DESC = "TestDesc";

  /**
   * Test user.
   */
  private static final String TEST_USER = "TestUser";

  /**
   * Test group.
   */
  private static final String TEST_GROUP = "testGroup";

  /**
   * Test host and domain.
   */
  private static final String SMARTCAMPUS = "smartcampus";

  /**
   * Shared Roster Service implementation.
   */
  @InjectMocks
  private SharedRosterServiceImpl sharedRosterService;

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
   * Test add user with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testAddUserToGroupOkResponse() {
    // given
    final AddUserRequest addUserRequest =
        AddUserRequest.builder().host(SMARTCAMPUS).group(TEST_GROUP).user(TEST_USER).build();
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_USER_COMMAND, addUserRequest))
            .willReturn(OK_RESPONSE);

    // when
    sharedRosterService.addUserToGroup(TEST_USER, TEST_GROUP);

    // then

  }

  /**
   * Test add user with BAD_REQUEST response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testAddUserToGroupBadResponse() {
    // given
    final AddUserRequest addUserRequest =
        AddUserRequest.builder().host(SMARTCAMPUS).group(TEST_GROUP).user(TEST_USER).build();
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_USER_COMMAND, addUserRequest))
            .willReturn(BAD_RESPONSE);

    // when
    sharedRosterService.addUserToGroup(TEST_USER, TEST_GROUP);

    // then

  }

  /**
   * Test create new group with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testCreateNewGroupOkResponse() {
    // given
    final CreateGroupRequest createGroupRequest =
        CreateGroupRequest.builder().host(SMARTCAMPUS).group(TEST_GROUP).description(TEST_DESC)
            .name(TEST_GROUP).display(DISPLAYED_GROUPS_PARAM).build();
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_COMMAND, createGroupRequest))
            .willReturn(OK_RESPONSE);

    // when
    sharedRosterService.createNewGroup(TEST_GROUP, TEST_GROUP, TEST_DESC,
        Arrays.asList(DISPLAYED_GROUPS));

    // then
  }

  /**
   * Test create new group with BAD_REQUEST response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testCreateNewGroupBadResponse() {
    // given
    final CreateGroupRequest createGroupRequest =
        CreateGroupRequest.builder().host(SMARTCAMPUS).group(TEST_GROUP).description(TEST_DESC)
            .name(TEST_GROUP).display(DISPLAYED_GROUPS_PARAM).build();
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_COMMAND, createGroupRequest))
            .willReturn(BAD_RESPONSE);

    // when
    sharedRosterService.createNewGroup(TEST_GROUP, TEST_GROUP, TEST_DESC,
        Arrays.asList(DISPLAYED_GROUPS));

    // then
  }

  /**
   * Test delete user from group with OK response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testDeleteUserFromGroupOkResponse() {
    // given
    final DeleteUseRequest deleteUseRequest =
        DeleteUseRequest.builder().host(SMARTCAMPUS).user(TEST_USER).group(TEST_GROUP).build();
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_DELETE_USER_COMMAND, deleteUseRequest))
            .willReturn(OK_RESPONSE);

    // when
    sharedRosterService.deleteUserFromGroup(TEST_USER, TEST_GROUP);

    // then
  }

  /**
   * Test delete user from group with BAD_REQUEST response.
   */
  @Test
  @SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
  public void testDeleteUserFromGroupBadResponse() {
    // given
    final DeleteUseRequest deleteUseRequest =
        DeleteUseRequest.builder().host(SMARTCAMPUS).user(TEST_USER).group(TEST_GROUP).build();
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_DELETE_USER_COMMAND, deleteUseRequest))
            .willReturn(BAD_RESPONSE);

    // when
    sharedRosterService.deleteUserFromGroup(TEST_USER, TEST_GROUP);

    // then
  }

  /**
   * Test get group information.
   */
  @Test
  public void testGetGroupInformation() {
    // given
    final InformationRequest informationRequest =
        InformationRequest.builder().host(SMARTCAMPUS).group(TEST_GROUP).build();

    HashMap<Object, Object> entity = new HashMap<>();
    entity.put(TEST_KEY, TEST_VALUE);
    given(clientResponseProvider.sendPostRequest(
        SharedRosterGroupConstants.SHARED_ROSTER_GROUP_INFO_COMMAND, informationRequest))
            .willReturn(Response.ok().entity(entity).build());

    // when
    Map<String, String> groupInformation = sharedRosterService.getGroupInformation(TEST_GROUP);

    // then
    Assert.assertEquals(TEST_VALUE, groupInformation.get(TEST_KEY));
  }

  /**
   * Test get group list.
   */
  @Test
  public void testGetGroupList() {
    // given
    final GroupRequest groupRequest = GroupRequest.builder().host(SMARTCAMPUS).build();
    given(clientResponseProvider
        .sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_LIST_COMMAND, groupRequest))
            .willReturn(Response.ok().entity(GROUP_LIST).build());

    // when
    List<String> groupList = sharedRosterService.getGroupList();

    // then
    Assert.assertThat(groupList.toArray(),
        Matchers.arrayContainingInAnyOrder(GROUP_LIST.toArray(new String[GROUP_LIST.size()])));
  }

}
