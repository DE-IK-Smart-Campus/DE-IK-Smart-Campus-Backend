package hu.unideb.smartcampus.service.api.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.UserConsultingDateRepository;
import hu.unideb.smartcampus.service.api.converter.toiq.UserConsultingDateEntityToStudentIqElementConverter;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * Test for {@link UserConsultingDateServiceImpl}
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LocalDateTime.class})
public class UserConsultingDateServiceImplTest {

  private static final String DATE = "Monday 10-12";

  private static final String USERNAME = "testUser";

  private static final String FULL_NAME = "Test User";

  private static final String THESIS = "Thesis";

  private static final String DURATION = "10 minutes";

  private static final String INSTRUCTORID = "ABC123";

  private static final List<UserConsultingDateEntity> RETURNED_LIST =
      Arrays.asList(UserConsultingDateEntity.builder()
          .user(
              UserEntity.builder()
                  .username(USERNAME)
                  .fullName(FULL_NAME)
                  .build())
          .consultingDate(
              ConsultingDateEntity.builder()
                  .id(1L)
                  .date(DATE)
                  .fromToDate(
                      FromToDateEmbeddedEntity.builder()
                          .fromDate(LocalDateTime.now())
                          .toDate(LocalDateTime.now())
                          .build())
                  .sum(0)
                  .build())
          .duration(DURATION)
          .reason(THESIS)
          .build());

  private static final List<StudentIqElement> STUDENTS =
      Arrays.asList(StudentIqElement.builder()
          .studentName(FULL_NAME)
          .reason(THESIS)
          .duration(DURATION)
          .build());

  private static final List<InstructorConsultingDateIqElement> EXPECTED_LIST =
      Arrays.asList(InstructorConsultingDateIqElement.builder()
          .students(STUDENTS)
          .day(DATE)
          .consultingDateId(1L)
          .build());

  @InjectMocks
  private UserConsultingDateServiceImpl service;

  @Mock
  private UserConsultingDateRepository userConsultingDateRepository;

  @Spy
  private UserConsultingDateEntityToStudentIqElementConverter converter =
      new UserConsultingDateEntityToStudentIqElementConverter();

  /**
   * Test findSignedStudentByInstructorIdWithinOneWeek.
   */
  @Test
  @Ignore
  public void findSignedStudentByInstructorIdWithinOneWeek() {
    // given
    LocalDateTime fromLocalDateTime = LocalDateTime.of(2000, 1, 1, 8, 0);
    LocalDateTime toLocalDateTime = LocalDateTime.of(2000, 1, 8, 8, 0);

    // when
    PowerMockito.mockStatic(LocalDateTime.class);
    PowerMockito.when(LocalDateTime.now()).thenReturn(fromLocalDateTime);
    PowerMockito.when(LocalDateTime.now().plusWeeks(1)).thenReturn(toLocalDateTime);
    PowerMockito.when(userConsultingDateRepository
        .getUserConsultingDatesByInstructorIdBetweenRange(INSTRUCTORID, fromLocalDateTime,
            toLocalDateTime))
        .thenReturn(RETURNED_LIST);

    // then
    List<InstructorConsultingDateIqElement> findSignedStudentByInstructorIdWithinOneWeek =
        service.findSignedStudentByInstructorIdWithinOneWeek(INSTRUCTORID);
    Assert.assertEquals(EXPECTED_LIST, findSignedStudentByInstructorIdWithinOneWeek);
  }

  /**
   * Test listSignedStudentByInstructorId
   */
  @Test
  public void listSignedStudentByInstructorId() {
    // given


    // when
    PowerMockito.when(userConsultingDateRepository
        .getUserConsultingDatesByInstructorId(INSTRUCTORID))
        .thenReturn(RETURNED_LIST);

    // then
    List<InstructorConsultingDateIqElement> listSignedStudentByInstructorId =
        service.listSignedStudentByInstructorId(INSTRUCTORID);
    Assert.assertEquals(EXPECTED_LIST, listSignedStudentByInstructorId);
  }
}
