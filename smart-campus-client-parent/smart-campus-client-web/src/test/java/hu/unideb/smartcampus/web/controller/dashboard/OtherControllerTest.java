package hu.unideb.smartcampus.web.controller.dashboard;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.Principal;
import hu.unideb.smartcampus.web.controller.AbstractControllerTest;

@RunWith(MockitoJUnitRunner.class)
public class OtherControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_DASHBOARD_OTHER = "/dashboard/other";
  private static final String VIEW_NAME_DASHBOARD_OTHER = "dashboard/other";
  private static final String PRINCIPAL_NAME = "principalUsername";

  @InjectMocks
  private OtherController otherController;

  @Mock
  private Principal principal;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.otherController};
  }

  @Test
  public void loadOtherViewShouldLoadOtherView() throws Exception {
    when(principal.getName()).thenReturn(PRINCIPAL_NAME);
    this.mockMvc.perform(get(REQUEST_URL_DASHBOARD_OTHER).principal(principal))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_DASHBOARD_OTHER))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_DASHBOARD_OTHER + VIEW_SUFFIX));
  }
}
