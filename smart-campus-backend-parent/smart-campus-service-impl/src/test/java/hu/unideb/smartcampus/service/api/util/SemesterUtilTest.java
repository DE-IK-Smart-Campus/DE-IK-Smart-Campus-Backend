package hu.unideb.smartcampus.service.api.util;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test for {@link SemesterUtil}
 */
@PrepareForTest({LocalDate.class, SemesterUtil.class})
@RunWith(PowerMockRunner.class)
public class SemesterUtilTest {

  private static final LocalDate IN_FIRST_SEMESTER = LocalDate.of(2017, 9, 17);
  private SemesterUtil semesterUtil = new SemesterUtil();

  @Test
  public void testGetEndPeriodInLong() throws Exception {
    // given
    PowerMockito.mockStatic(LocalDate.class);

    // when
    PowerMockito.when(LocalDate.now()).thenReturn(IN_FIRST_SEMESTER);

    // then
    Long endPeriodInLong = semesterUtil.getEndPeriodInLong();

    Assert.assertEquals(
        SemesterUtil.FIRST_SEMESTER_END.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(0)),
        endPeriodInLong.longValue());
  }
  
  @Test
  public void testGetStartPeriodInLong() throws Exception {
    // given
    PowerMockito.mockStatic(LocalDate.class);

    // when
    PowerMockito.when(LocalDate.now()).thenReturn(IN_FIRST_SEMESTER);

    // then
    Long startPeriodInLong = semesterUtil.getStartPeriodInLong();

    Assert.assertEquals(
        SemesterUtil.FIRST_SEMESTER_START.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(0)),
        startPeriodInLong.longValue());
  }


  @Test
  public void testGetEndPeriod() {
    LocalDate endPeriod = semesterUtil.getEndPeriod("2016/17/1");
    Assert.assertEquals(LocalDate.of(2016, 12, 31), endPeriod);
    endPeriod = semesterUtil.getEndPeriod("2016/17/2");
    Assert.assertEquals(LocalDate.of(2017, 5, 31), endPeriod);
  }

  @Test
  public void testGetStartPeriod() {
    LocalDate startPeriod = semesterUtil.getStartPeriod("2016/17/1");
    Assert.assertEquals(LocalDate.of(2016, 9, 1), startPeriod);
    startPeriod = semesterUtil.getStartPeriod("2016/17/2");
    Assert.assertEquals(LocalDate.of(2017, 2, 1), startPeriod);
  }

}
