package hu.unideb.smartcampus.service.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

  private DateUtil dateUtil = new DateUtil();
  // 2017. 04. 01. 2:00:00
  private Long period = 1491004800L;
  
  // 976533825
  private LocalDate localDate = LocalDate.of(2000, 12, 11);
  private LocalDateTime localDateTime = LocalDateTime.of(2000, 12, 11, 14, 23, 45);

  @Test
  public void testGetInLocalDateByEpochSecond() {
    LocalDate localDate = dateUtil.getInLocalDateByEpochSecond(period);
    Assert.assertEquals(LocalDate.of(2017, 4, 1), localDate);
  }

  @Test
  public void testGetInLocalDateTimeByEpochSecond() {
    LocalDateTime localDateTime = dateUtil.getInLocalDateTimeByEpochSecond(period);
    Assert.assertEquals(LocalDateTime.of(2017, 4, 1, 2, 0, 0), localDateTime);
  }

  @Test
  public void testGetInLocalTimeByEpochSecond() {
    LocalTime localTime = dateUtil.getInLocalTimeByEpochSecond(period);
    Assert.assertEquals(LocalTime.of(2, 0, 0), localTime);
  }

  @Test
  public void testGetInEpochLongByLocalDate() {
    Long longLocalDate = dateUtil.getInEpochLongByLocalDate(localDate);
    Assert.assertEquals(976485600L, longLocalDate.longValue());
  }

  @Test
  public void testGetInEpochLongByLocalDateTime() {
    Long longLocalDateTime = dateUtil.getInEpochLongByLocalDateTime(localDateTime);
    Assert.assertEquals(976537425L, longLocalDateTime.longValue());
  }

}
