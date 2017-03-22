package hu.unideb.smartcampus.web.controller.dashboard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.AbstractControllerTest;
import hu.unideb.smartcampus.web.controller.DashboardRootController;

@RunWith(MockitoJUnitRunner.class)
public class DashboardRootControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD = "/dashboard";
  private static final String REDIRECT_URL_DASHBOARD_HOME = "/dashboard/home";

  @InjectMocks
  private DashboardRootController dashboardRootController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.dashboardRootController};
  }

  @Test
  public void redirectToHomePathShouldRenderHomeView() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl(REDIRECT_URL_DASHBOARD_HOME));
  }
}
