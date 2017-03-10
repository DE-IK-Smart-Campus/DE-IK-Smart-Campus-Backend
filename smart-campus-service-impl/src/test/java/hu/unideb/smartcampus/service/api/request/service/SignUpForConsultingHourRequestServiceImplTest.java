package hu.unideb.smartcampus.service.api.request.service;

import org.junit.Assert;
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
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SignUpForConsultingHourWrapper;
import hu.unideb.smartcampus.shared.requestmessages.SignUpForConsultingHourRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;


/**
 * Test for {@link SignUpForConsultingHourRequestServiceImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class SignUpForConsultingHourRequestServiceImplTest {

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
  private static final long USER_ID = 2L;

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

  /**
   * Consulting date entity.
   */
  private static final ConsultingDateEntity CONSULTING_DATE_ENTITY =
      ConsultingDateEntity.builder().date(CONSULTING_DATE_DATE).build();

  /**
   * Service impl.
   */
  @InjectMocks
  private SignUpForConsultingHourRequestServiceImpl service =
      new SignUpForConsultingHourRequestServiceImpl();

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


  /**
   * Test get response with exsisting date entity.
   */
  @Test
  public void getResponseWithNotNullDateEntityShouldReturnOkResponse() {
    // given
    SignUpForConsultingHourRequest request = SignUpForConsultingHourRequest.builder()
        .consultingHourId(CONSULTING_DATE_ID).userId(USER_ID)
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_REQUEST)
        .duration("10 minutes").reason("Discuss something important").build();

    // when
    Mockito.when(userRepository.findOne(USER_ID)).thenReturn(USER_ENTITY);
    Mockito.when(consultingDateRepository.findOne(CONSULTING_DATE_ID))
        .thenReturn(CONSULTING_DATE_ENTITY);

    // then
    SignUpForConsultingHourWrapper response = service.getResponse(request);
    Assert.assertEquals(OK, response.getStatus());
    Assert.assertEquals(Integer.valueOf(1), CONSULTING_DATE_ENTITY.getSum());
  }

  /**
   * Test get response with non-exsisting date entity.
   */
  @Test
  public void getResponseWithNullDateEntityShouldReturnErrorResponse() {
    // given
    SignUpForConsultingHourRequest request = SignUpForConsultingHourRequest.builder()
        .consultingHourId(CONSULTING_DATE_ID).userId(USER_ID)
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_REQUEST)
        .duration("10 minutes").reason("Discuss something important").build();

    // when
    Mockito.when(userRepository.findOne(USER_ID)).thenReturn(USER_ENTITY);
    Mockito.when(consultingDateRepository.findOne(CONSULTING_DATE_ID)).thenReturn(null);

    // then
    SignUpForConsultingHourWrapper response = service.getResponse(request);
    Assert.assertEquals(NO_CONSULTING_DATE_EXISTS, response.getStatus());
  }

  /**
   * Test get response with non-exsisting user.
   */
  @Test
  public void getResponseWithNullUserEntityShouldReturnErrorResponse() {
    // given
    SignUpForConsultingHourRequest request = SignUpForConsultingHourRequest.builder()
        .consultingHourId(CONSULTING_DATE_ID).userId(USER_ID)
        .messageType(RequestMessagesConstants.SIGN_UP_FOR_CONSULTING_HOUR_REQUEST)
        .duration("10 minutes").reason("Discuss something important").build();

    // when
    Mockito.when(userRepository.findOne(USER_ID)).thenReturn(null);
    Mockito.when(consultingDateRepository.findOne(CONSULTING_DATE_ID))
        .thenReturn(CONSULTING_DATE_ENTITY);

    // then
    SignUpForConsultingHourWrapper response = service.getResponse(request);
    Assert.assertEquals(USER_DOES_NOT_EXISTS, response.getStatus());
  }



}
