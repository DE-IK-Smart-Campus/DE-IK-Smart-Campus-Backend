package hu.unideb.smartcampus.web.controller.dashboard;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.AbstractControllerTest;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD_HOME = "/dashboard/home";
  private static final String VIEW_NAME_DASHBOARD_HOME = "dashboard/home";
  private static final String PRINCIPAL_NAME = "principalUsername";

  @InjectMocks
  private HomeController homeController;

  @Mock
  private Principal principal;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[] {this.homeController};
  }

  @Test
  @Ignore
  public void loadHomeViewShouldLoadHomeView() throws Exception {
    when(principal.getName()).thenReturn(PRINCIPAL_NAME);
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_HOME).principal(principal))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_HOME))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_HOME + VIEW_SUFFIX));
  }
}
