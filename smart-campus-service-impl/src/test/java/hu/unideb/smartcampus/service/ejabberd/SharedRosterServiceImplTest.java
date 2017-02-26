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

@RunWith(MockitoJUnitRunner.class)
public class SharedRosterServiceImplTest {

  private static final String ENDLINE = "\n";

  private static final String TEST_GROUP_TWO = "Test Group Two";

  private static final String TEST_GROUP_ONE = "Test Group One";

  private static final List<String> GROUP_LIST = Arrays.asList(TEST_GROUP_ONE,TEST_GROUP_TWO);

  private static final Response OK_RESPONSE = Response.status(Status.OK).build();

  private static final Response BAD_RESPONSE = Response.status(Status.BAD_REQUEST).build();

  private static final String TEST_VALUE = "Test value";

  private static final String TEST_KEY = "Test key";

  private static final String DISPLAYED_GROUPS = "displayedGroups";

  private static final String DISPLAYED_GROUPS_PARAM = DISPLAYED_GROUPS + ENDLINE;

  private static final String TEST_DESC = "TestDesc";

  private static final String TEST_USER = "TestUser";

  private static final String TEST_GROUP = "testGroup";

  private static final String SMARTCAMPUS = "smartcampus";

  @InjectMocks
  private SharedRosterServiceImpl sharedRosterService;

  @Mock
  private ClientResponseProvider clientResponseProvider;

  @Mock
  private PropertyProvider propertyProvider;

  @Spy
  private ResponseStatusValidatorImpl statusValidator;

  @Before
  public void setUp() throws Exception {
    given(propertyProvider.getPropertyValue(ConfigPropertyKey.SMART_CAMPUS_XMPP_DOMAIN)).willReturn(SMARTCAMPUS);
  }

  @Test
  public void testAddUserToGroupOkResponse() {
    //given
    final AddUserRequest addUserRequest = AddUserRequest.builder()
        .host(SMARTCAMPUS)
        .group(TEST_GROUP)
        .user(TEST_USER)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_USER_COMMAND, addUserRequest)).willReturn(OK_RESPONSE);

    //when
    sharedRosterService.addUserToGroup(TEST_USER,TEST_GROUP);

    //then

  }

  @Test
  public void testAddUserToGroupBadResponse() {
    //given
    final AddUserRequest addUserRequest = AddUserRequest.builder()
        .host(SMARTCAMPUS)
        .group(TEST_GROUP)
        .user(TEST_USER)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_USER_COMMAND, addUserRequest)).willReturn(BAD_RESPONSE);

    //when
    sharedRosterService.addUserToGroup(TEST_USER,TEST_GROUP);

    //then

  }

  @Test
  public void testCreateNewGroupOkResponse() {
    //given
    final CreateGroupRequest createGroupRequest = CreateGroupRequest.builder()
        .host(SMARTCAMPUS)
        .group(TEST_GROUP)
        .description(TEST_DESC)
        .name(TEST_GROUP)
        .display(DISPLAYED_GROUPS_PARAM)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_COMMAND, createGroupRequest)).willReturn(OK_RESPONSE);

    //when
    sharedRosterService.createNewGroup(TEST_GROUP, TEST_GROUP, TEST_DESC, Arrays.asList(DISPLAYED_GROUPS));

    //then
  }

  @Test
  public void testCreateNewGroupBadResponse() {
    //given
    final CreateGroupRequest createGroupRequest = CreateGroupRequest.builder()
        .host(SMARTCAMPUS)
        .group(TEST_GROUP)
        .description(TEST_DESC)
        .name(TEST_GROUP)
        .display(DISPLAYED_GROUPS_PARAM)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_CREATE_COMMAND, createGroupRequest)).willReturn(BAD_RESPONSE);

    //when
    sharedRosterService.createNewGroup(TEST_GROUP, TEST_GROUP, TEST_DESC, Arrays.asList(DISPLAYED_GROUPS));

    //then
  }

  @Test
  public void testDeleteUserFromGroupOkResponse() {
    //given
    final DeleteUseRequest deleteUseRequest = DeleteUseRequest.builder()
        .host(SMARTCAMPUS)
        .user(TEST_USER)
        .group(TEST_GROUP)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_DELETE_USER_COMMAND, deleteUseRequest)).willReturn(OK_RESPONSE);

    //when
    sharedRosterService.deleteUserFromGroup(TEST_USER,TEST_GROUP);

    //then
  }

  @Test
  public void testDeleteUserFromGroupBadResponse() {
    //given
    final DeleteUseRequest deleteUseRequest = DeleteUseRequest.builder()
        .host(SMARTCAMPUS)
        .user(TEST_USER)
        .group(TEST_GROUP)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_DELETE_USER_COMMAND, deleteUseRequest)).willReturn(BAD_RESPONSE);

    //when
    sharedRosterService.deleteUserFromGroup(TEST_USER,TEST_GROUP);

    //then
  }

  @Test
  public void testGetGroupInformation() {
    //given
    final InformationRequest informationRequest = InformationRequest.builder()
        .host(SMARTCAMPUS)
        .group(TEST_GROUP)
        .build();

    HashMap<Object, Object> entity = new HashMap<>();
    entity.put(TEST_KEY, TEST_VALUE);
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_INFO_COMMAND, informationRequest)).willReturn(Response.ok().entity(entity
        ).build());

    //when
    Map<String, String> groupInformation = sharedRosterService.getGroupInformation(TEST_GROUP);

    //then
    Assert.assertEquals(TEST_VALUE, groupInformation.get(TEST_KEY));
  }

  @Test
  public void testGetGroupList() {
    //given
    final GroupRequest groupRequest = GroupRequest.builder()
        .host(SMARTCAMPUS)
        .build();
    given(clientResponseProvider.sendPostRequest(SharedRosterGroupConstants.SHARED_ROSTER_GROUP_LIST_COMMAND, groupRequest)).willReturn(Response.ok().entity(GROUP_LIST).build());

    //when
    List<String> groupList = sharedRosterService.getGroupList();

    //then
    Assert.assertThat(groupList.toArray(), Matchers.arrayContainingInAnyOrder(GROUP_LIST.toArray(new String[GROUP_LIST.size()])));
  }

}
