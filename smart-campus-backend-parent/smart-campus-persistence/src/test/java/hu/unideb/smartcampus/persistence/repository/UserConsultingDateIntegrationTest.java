package hu.unideb.smartcampus.persistence.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;

/**
 * Test for {@link UserConsultingDateRepository}.
 */
public class UserConsultingDateIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  private static final String MONDAY_10_12 = "Monday 10-12";

  @Autowired
  private UserConsultingDateRepository userConsultingDateRepository;

  private static final Long ADMIN_ID = 1L;

  private static final Long INSTRUCTOR_ID = 2L;

  /**
   * Test getUserConsultingDatesByConsultingDateId.
   * 
   * @throws Exception
   */
  @Test
  public void getUserConsultingDatesByConsultingDateId() throws Exception {
    List<UserConsultingDateEntity> userConsultingDatesByConsultingDateId =
        userConsultingDateRepository
            .getUserConsultingDatesByConsultingDateId(Arrays.asList(ADMIN_ID));

    Assert.assertEquals(3, userConsultingDatesByConsultingDateId.size());
  }

  /**
   * Test getUserConsultingDatesByInstructorId.
   */
  @Test
  public void getUserConsultingDatesByInstructorId() {
    List<UserConsultingDateEntity> instructorConsultingDate =
        userConsultingDateRepository
            .getUserConsultingDatesByInstructorId(INSTRUCTOR_ID);
    Assert.assertEquals(1, instructorConsultingDate.size());
  }

  /**
   * Test getUserConsultingDatesByInstructorIdBetweenRange.
   */
  @Test
  public void getUserConsultingDatesByInstructorIdBetweenRange() {
    // 10-04-2017 08:00:00
    // 10-04-2017 10:00:00
    List<UserConsultingDateEntity> instructorConsultingDate =
        userConsultingDateRepository
            .getUserConsultingDatesByInstructorIdBetweenRange(INSTRUCTOR_ID,
                Timestamp.valueOf(LocalDateTime.of(2017, Month.APRIL, 10, 8, 0)),
                Timestamp.valueOf(LocalDateTime.of(2017, Month.APRIL, 10, 10, 0)));
    Assert.assertEquals(1, instructorConsultingDate.size());
    Assert.assertEquals(MONDAY_10_12,
        instructorConsultingDate.get(0).getConsultingDate().getDateInString());
  }
}
