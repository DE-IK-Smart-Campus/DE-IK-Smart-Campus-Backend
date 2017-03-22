package hu.unideb.smartcampus.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.dashboard.HomeController;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD_HOME = "/dashboard/home";
  private static final String VIEW_NAME_DASHBOARD_HOME = "dashboard/home";

  @InjectMocks
  private HomeController homeController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.homeController};
  }

  @Test
  public void loadHomeViewShouldLoadHomeView() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_HOME))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_HOME))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_HOME + VIEW_SUFFIX));
  }
}
