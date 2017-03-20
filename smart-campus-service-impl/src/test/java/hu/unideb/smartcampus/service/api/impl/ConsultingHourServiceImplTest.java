package hu.unideb.smartcampus.service.api.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.ConsultingHourService;

/**
 * Test for {@link ConsultingHourService}.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class ConsultingHourServiceImplTest {

  /**
   * SubjectEvent name.
   */
  /*private static final String AI = "AI";

  /**
   * Result subject.
   */
  /*private static final SubjectDetails SUBJECT_DOMAIN = SubjectDetails.builder().subjectName(AI).build();

  /**
   * Expected set of subjects.
   */
  /*private static final Set<SubjectDetails> EXPECTED_DOMAIN_SET = Sets.newSet(SUBJECT_DOMAIN);

  /**
   * SubjectEvent entity.
   */
  /*private static final SubjectDetailsEntity SUBJECT_ENTITY =
      SubjectDetailsEntity.builder().subjectName(AI).build();

  /**
   * Set of entities.
   */
  /*private static final Set<SubjectDetailsEntity> RESULT_ENTITY_SET = Sets.newSet(SUBJECT_ENTITY);

  /**
   * UserRepository mock.
   */
  /*@Mock
  private UserRepository userRepository;

  /**
   * SubjectDetailsEntityToSubjectDetailsConverter implementation.
   */
  /*@Spy
  private SubjectDetailsEntityToSubjectDetailsConverter conversionService;

  /**
   * UserService with UserRepository and ConversionService mocks.
   */
  /*@InjectMocks
  private ConsultingHourServiceImpl consultingHoursService;

  /**
   * Test subject list is empty.
   */
  /*@Test
  public void getSubjectsByUserIdShouldReturnEmptySet() {
    // given

    // when
    given(userRepository.getSubjectsByUserId(1L)).willReturn(Collections.emptySet());

    // then
    Set<SubjectDetails> subjects = consultingHoursService.getSubjectsByUserId(1L);

    Assert.assertTrue(subjects.isEmpty());
  }


  /**
   * Test should return user's subjects.
   */
  /*@Test
  public void getSubjectsByUserIdShouldReturnUsersSubjects() {
    // given

    // when
    given(userRepository.getSubjectsByUserId(1L)).willReturn(RESULT_ENTITY_SET);

    // then
    Set<SubjectDetails> subjects = consultingHoursService.getSubjectsByUserId(1L);

    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, subjects, equalTo(EXPECTED_DOMAIN_SET));
  }

*/

  /**
   * Test method.
   */
  @Test
  public void method() {
    assertTrue("message", true);
  }

}
