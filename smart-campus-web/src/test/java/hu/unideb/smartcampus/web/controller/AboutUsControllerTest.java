package hu.unideb.smartcampus.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.dashboard.AboutUsController;

@RunWith(MockitoJUnitRunner.class)
public class AboutUsControllerTest extends AbstractControllerTest{

  private static final String REQUEST_URL_DASHBOARD_ABOUT_US = "/dashboard/about-us";
  private static final String VIEW_NAME_DASHBOARD_ABOUT_US = "dashboard/about-us";

  @InjectMocks
  private AboutUsController aboutUsController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.aboutUsController};
  }

  @Test
  public void loadAboutUsViewShouldLoadAboutUsView() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_ABOUT_US))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_ABOUT_US))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_ABOUT_US + VIEW_SUFFIX));
  }
}
