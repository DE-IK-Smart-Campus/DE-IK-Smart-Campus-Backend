package hu.unideb.smartcampus.service.api.request.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.ConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.UserConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;
import hu.unideb.smartcampus.shared.wrapper.SignUpForConsultingHourWrapper;


/**
 * Test for {@link SignUpForConsultingDateRequestServiceImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class SignUpForConsultingDateRequestServiceImplTest {

  /**
   * Consulting date date.
   */
  private static final String CONSULTING_DATE_DATE = "Friday 10-12";

  /**
   * User's username.
   */
  private static final String TEST_USER = "TestUser";

  /**
   * Consulting date id.
   */
  private static final long CONSULTING_DATE_ID = 1L;

  /**
   * User id.
   */
  private static final String USER_ID = "TestUser";

  /**
   * Example duration.
   */
  private static final String EXAMPLE_DURATION = "10 minutes";

  /**
   * Example reason.
   */
  private static final String EXAMPLE_REASON = "Discuss something important";

  /**
   * Example reqeust.
   */
  private static final SignUpForConsultingHourRequest EXAMPLE_REQUEST =
      SignUpForConsultingHourRequest.builder().consultingHourId(CONSULTING_DATE_ID).userId(USER_ID)
          .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_REQUEST)
          .duration(EXAMPLE_DURATION).reason(EXAMPLE_REASON).build();

  /**
   * User does not exists response status.
   */
  private static final String USER_DOES_NOT_EXISTS = "User does not exists.";

  /**
   * No consulting date exists response status.
   */
  private static final String NO_CONSULTING_DATE_EXISTS = "No consulting date exists.";

  /**
   * OK status.
   */
  private static final String OK = "OK";

  /**
   * User entity.
   */
  private static final UserEntity USER_ENTITY = UserEntity.builder().username(TEST_USER).build();


  private static ConsultingDateEntity createConsultingDateEntity() {
    return ConsultingDateEntity.builder().date(CONSULTING_DATE_DATE).build();
  }

  /**
   * Service impl.
   */
  @InjectMocks
  private SignUpForConsultingDateRequestServiceImpl service;

  /**
   * User repository mock.
   */
  @Mock
  private UserRepository userRepository;

  /**
   * Consulting date repository mock.
   */
  @Mock
  private ConsultingDateRepository consultingDateRepository;


  /**
   * User consulting date repository mock.
   */
  @Mock
  private UserConsultingDateRepository userConsultingDateRepository;
  
  @Mock
  private CustomEventService customEventService;

  /**
   * Test get response with exsisting date entity.
   */
  @Test
  @Ignore
  public void getResponseWithNotNullDateEntityShouldReturnOkResponse() {
    // given
    ConsultingDateEntity consultingDateEntity = createConsultingDateEntity();

    // when
    Mockito.when(userRepository.findByUsername(USER_ID)).thenReturn(USER_ENTITY);
    Mockito.when(consultingDateRepository.findOne(CONSULTING_DATE_ID))
        .thenReturn(consultingDateEntity);

    // then
    SignUpForConsultingHourWrapper response = service.signUpByRequest(EXAMPLE_REQUEST);
    Assert.assertEquals(OK, response.getStatus());
    Assert.assertEquals(Integer.valueOf(1), consultingDateEntity.getSum());
  }

  /**
   * Test get response with non-exsisting date entity.
   */
  @Test
  @Ignore
  public void getResponseWithNullDateEntityShouldReturnErrorResponse() {
    // given

    // when
    Mockito.when(userRepository.findByUsername(USER_ID)).thenReturn(USER_ENTITY);
    Mockito.when(consultingDateRepository.findOne(CONSULTING_DATE_ID)).thenReturn(null);

    // then
    SignUpForConsultingHourWrapper response = service.signUpByRequest(EXAMPLE_REQUEST);
    Assert.assertEquals(NO_CONSULTING_DATE_EXISTS, response.getStatus());
  }


  /**
   * Test get response with non-exsisting user.
   */
  @Test
  @Ignore
  public void getResponseWithNullUserEntityShouldReturnErrorResponse() {
    // given
    ConsultingDateEntity consultingDateEntity = createConsultingDateEntity();

    // when
    Mockito.when(userRepository.findByUsername(USER_ID)).thenReturn(null);
    Mockito.when(consultingDateRepository.findOne(CONSULTING_DATE_ID))
        .thenReturn(consultingDateEntity);

    // then
    SignUpForConsultingHourWrapper response = service.signUpByRequest(EXAMPLE_REQUEST);
    Assert.assertEquals(USER_DOES_NOT_EXISTS, response.getStatus());
  }



}
