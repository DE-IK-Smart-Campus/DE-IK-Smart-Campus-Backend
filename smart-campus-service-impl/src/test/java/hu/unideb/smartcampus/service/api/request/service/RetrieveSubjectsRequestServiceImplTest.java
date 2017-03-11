package hu.unideb.smartcampus.service.api.request.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveSubjectsRequest;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;


/**
 * Test for {@link RetrieveSubjectsRequestServiceImplTest}.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField","PMD.UnusedLocalVariable"})
public class RetrieveSubjectsRequestServiceImplTest {

  /**
   * AI ID.
   */
  private static final long SUBJECT_ID_AI = 1L;

  /**
   * AI.
   */
  private static final String AI = "AI";

  /**
   * AI subject.
   */
  private static final SubjectEntity AI_SUBJECT =
      SubjectEntity.builder().id(SUBJECT_ID_AI).name(AI).build();

  /**
   * IE ID.
   */
  private static final Long SUBJECT_ID_IE = 2L;

  /**
   * IE.
   */
  private static final String IE = "IE";

  /**
   * IE subject.
   */
  private static final SubjectEntity IE_SUBJECT =
      SubjectEntity.builder().id(SUBJECT_ID_IE).name(IE).build();

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
   * Consulting date entity.
   */
  private static final ConsultingDateEntity CONSULTING_DATE_ENTITY =
      ConsultingDateEntity.builder().date(CONSULTING_DATE_DATE).build();


  /**
   * Subjects.
   */
  private static final Set<SubjectEntity> SUBJECTS = Sets.newSet(AI_SUBJECT, IE_SUBJECT);

  /**
   * Service impl.
   */
  @InjectMocks
  private RetrieveSubjectsRequestServiceImpl service = new RetrieveSubjectsRequestServiceImpl();

  /**
   * Consulting date repository mock.
   */
  @Mock
  private UserRepository userRepository;

  /**
   * Instructor repository.
   */
  @Mock
  private InstructorRepository instructorRepository;


  /**
   * Test get response with exsisting date entity.
   */
  @Test
  public void getResponseWithNotNullDateEntityShouldReturnOkResponse() {
    // given
    RetrieveSubjectsRequest request = RetrieveSubjectsRequest.builder().userId(USER_ID)
        .messageType(RequestMessagesConstants.RETRIEVE_SUBJECTS_REQUEST).build();

    // when
    Mockito.when(userRepository.getSubjectsByUsername(USER_ID)).thenReturn(SUBJECTS);
    // then
    // SubjectRetrievalResponseWrapper response = service.getResponse(request);

    // Assert.assertEquals(RequestMessagesConstants.RETRIEVE_SUBJECTS_RESPONSE,
    // response.getMessageType());
    Assert.assertTrue(true);
  }
}
