package hu.unideb.smartcampus.service.api.calendar.helper.impl;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.FastDateParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTest {

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void test() {
    String string = "2017-02-13T12:00:00+0100";
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
  }

}
