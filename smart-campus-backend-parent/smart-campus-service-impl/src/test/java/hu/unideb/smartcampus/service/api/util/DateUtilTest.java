package hu.unideb.smartcampus.service.api.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import hu.unideb.smartcampus.shared.util.DateUtil;

public class DateUtilTest {

  // 2017. 04. 01. 2:00:00
  private Long period = 1491004800L;
  
  // 976533825
  private LocalDate localDate = LocalDate.of(2000, 12, 11);
  private LocalDateTime localDateTime = LocalDateTime.of(2000, 12, 11, 14, 23, 45);

  @Test
  public void testGetInLocalDateByEpochSecond() {
    LocalDate localDate = DateUtil.getInLocalDateByEpochSecond(period);
    Assert.assertEquals(LocalDate.of(2017, 4, 1), localDate);
  }

  @Test
  public void testGetInLocalDateTimeByEpochSecond() {
    LocalDateTime localDateTime = DateUtil.getInLocalDateTimeByEpochSecond(period);
    Assert.assertEquals(LocalDateTime.of(2017, 4, 1, 2, 0, 0), localDateTime);
  }

  @Test
  public void testGetInLocalTimeByEpochSecond() {
    LocalTime localTime = DateUtil.getInLocalTimeByEpochSecond(period);
    Assert.assertEquals(LocalTime.of(2, 0, 0), localTime);
  }

  @Test
  public void testGetInEpochLongByLocalDate() {
    Long longLocalDate = DateUtil.getInEpochLongByLocalDate(localDate);
    Assert.assertEquals(976485600L, longLocalDate.longValue());
  }

  @Test
  public void testGetInEpochLongByLocalDateTime() {
    Long longLocalDateTime = DateUtil.getInEpochLongByLocalDateTime(localDateTime);
    Assert.assertEquals(976537425L, longLocalDateTime.longValue());
  }

}
