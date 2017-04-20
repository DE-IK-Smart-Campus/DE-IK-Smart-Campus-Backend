package hu.unideb.smartcampus.web.controller.dashboard;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.web.controller.AbstractControllerTest;

@RunWith(MockitoJUnitRunner.class)
public class CalendarControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD_CALENDAR = "/dashboard/calendar";
  private static final String VIEW_NAME_DASHBOARD_CALENDAR = "dashboard/calendar";
  private static final String PRINCIPAL_NAME = "principalUsername";

  @InjectMocks
  private CalendarController calendarController;

  @Mock
  private Principal principal;

  @Mock
  private CalendarService calendarService;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.calendarController};
  }

  @Test
  @Ignore
  public void loadCalendarViewShouldLoadCalendarView() throws Exception {
    when(principal.getName()).thenReturn(PRINCIPAL_NAME);
    when(calendarService.getCalendarSubjectEvents(anyLong(), anyLong())).thenReturn(Collections.emptyList());
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_CALENDAR).principal(principal))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_CALENDAR))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_CALENDAR + VIEW_SUFFIX));
  }
}
