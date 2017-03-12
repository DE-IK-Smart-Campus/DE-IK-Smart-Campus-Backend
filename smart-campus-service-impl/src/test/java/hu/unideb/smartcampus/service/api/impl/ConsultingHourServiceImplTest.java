package hu.unideb.smartcampus.service.api.impl;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.converter.todomain.SubjectEntityToSubjectConverter;
import hu.unideb.smartcampus.service.api.domain.Subject;

/**
 * Test for {@link ConsultingHourService}.
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({"PMD.UnusedPrivateField"})
public class ConsultingHourServiceImplTest {

  /**
   * Subject name.
   */
  private static final String AI = "AI";

  /**
   * Result subject.
   */
  private static final Subject SUBJECT_DOMAIN = Subject.builder().name(AI).build();

  /**
   * Expected set of subjects.
   */
  private static final Set<Subject> EXPECTED_DOMAIN_SET = Sets.newSet(SUBJECT_DOMAIN);

  /**
   * Subject entity.
   */
  private static final SubjectEntity SUBJECT_ENTITY =
      SubjectEntity.builder().id(1L).name(AI).build();

  /**
   * Set of entities.
   */
  private static final Set<SubjectEntity> RESULT_ENTITY_SET = Sets.newSet(SUBJECT_ENTITY);

  /**
   * UserRepository mock.
   */
  @Mock
  private UserRepository userRepository;

  /**
   * SubjectEntityToSubjectConverter implementation.
   */
  @Spy
  private SubjectEntityToSubjectConverter conversionService;

  /**
   * UserService with UserRepository and ConversionService mocks.
   */
  @InjectMocks
  private ConsultingHourServiceImpl consultingHoursService;

  /**
   * Test subject list is empty.
   */
  @Test
  public void getSubjectsByUserIdShouldReturnEmptySet() {
    // given

    // when
    given(userRepository.getSubjectsByUserId(1L)).willReturn(Collections.emptySet());

    // then
    Set<Subject> subjects = consultingHoursService.getSubjectsByUserId(1L);

    Assert.assertTrue(subjects.isEmpty());
  }


  /**
   * Test should return user's subjects.
   */
  @Test
  public void getSubjectsByUserIdShouldReturnUsersSubjects() {
    // given

    // when
    given(userRepository.getSubjectsByUserId(1L)).willReturn(RESULT_ENTITY_SET);

    // then
    Set<Subject> subjects = consultingHoursService.getSubjectsByUserId(1L);

    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, subjects, equalTo(EXPECTED_DOMAIN_SET));
  }



}
