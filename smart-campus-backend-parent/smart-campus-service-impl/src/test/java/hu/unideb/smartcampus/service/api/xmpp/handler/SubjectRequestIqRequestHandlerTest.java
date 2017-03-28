package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.jivesoftware.smack.packet.IQ;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.request.service.RetrieveSubjectsRequestService;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;
import hu.unideb.smartcampus.shared.wrapper.inner.InstructorWrapper;
import hu.unideb.smartcampus.shared.wrapper.inner.SubjectWrapper;

/**
 * Test SubjectRequestIqRequestHandler.
 */
@RunWith(MockitoJUnitRunner.class)
public class SubjectRequestIqRequestHandlerTest {

  /**
   * Instructor name.
   */
  private static final String INSTRUCTOR_NAME = "John";

  /**
   * Instructor ID.
   */
  private static final Long INSTRUCTOR_ID = 1L;

  /**
   * AI subject.
   */
  private static final String AI_SUBJECT = "AI";

  /**
   * AI instr.
   */
  private static final List<InstructorWrapper> AI_INSTR = Arrays.asList(
      InstructorWrapper.builder().instructorId(INSTRUCTOR_ID).name(INSTRUCTOR_NAME).build());

  /**
   * 
   */
  private static final SubjectWrapper AI_SUBJECT_WRAPPER =
      SubjectWrapper.builder().name(AI_SUBJECT).instructors(AI_INSTR).build();

  /**
   * User ID.
   */
  private static final String USER_ID = "user_id";

  /**
   * Subjects.
   */
  private static final List<SubjectWrapper> SUBJECTS = Arrays.asList(AI_SUBJECT_WRAPPER);

  /**
   * IQ request.
   */
  private static final IQ IQREQUEST = SubjectsIqRequest.builder().student(USER_ID).build();

  /**
   * Instructor IQ element.
   */
  private static final List<InstructorIqElement> INSTRUCTORS = Arrays.asList(
      InstructorIqElement.builder().instructorId(INSTRUCTOR_ID).name(INSTRUCTOR_NAME).build());

  /**
   * Excepted list.
   */
  private static final List<SubjectIqElement> EXPECTED = Arrays
      .asList(SubjectIqElement.builder().subjectName(AI_SUBJECT).instructors(INSTRUCTORS).build());

  /**
   * Handler.
   */
  @InjectMocks
  private SubjectRequestIqRequestHandler handler = new SubjectRequestIqRequestHandler();

  /**
   * Request service.
   */
  @Mock
  private RetrieveSubjectsRequestService requestService;

  @Test
  public void testHandleIQRequest() {
    // given

    // when
    Mockito.when(requestService.getSubjects(USER_ID)).thenReturn(SUBJECTS);

    // then
    SubjectsIqRequest handleIQRequest = (SubjectsIqRequest) handler.handleIQRequest(IQREQUEST);

    Assert.assertThat(EXPECTED, Matchers.is(handleIQRequest.getSubjects()));
  }

}
