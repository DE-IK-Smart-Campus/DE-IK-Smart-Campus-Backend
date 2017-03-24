package hu.unideb.smartcampus.web.controller.dashboard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.AbstractControllerTest;
import hu.unideb.smartcampus.web.controller.dashboard.CalendarController;

@RunWith(MockitoJUnitRunner.class)
public class CalendarControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD_CALENDAR = "/dashboard/calendar";
  private static final String VIEW_NAME_DASHBOARD_CALENDAR = "dashboard/calendar";

  @InjectMocks
  private CalendarController calendarController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.calendarController};
  }

  @Test
  public void loadCalendarViewShouldLoadCalendarView() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_CALENDAR))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_CALENDAR))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_CALENDAR + VIEW_SUFFIX));
  }
}
