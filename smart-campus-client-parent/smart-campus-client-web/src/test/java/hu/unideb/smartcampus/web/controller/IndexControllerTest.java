package hu.unideb.smartcampus.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_INDEX = "/index";
  private static final String VIEW_NAME_INDEX = "index";

  @InjectMocks
  private IndexController indexController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.indexController};
  }

  @Test
  public void loadIndexViewShouldRenderIndexView() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_INDEX))
        .andExpect(status().isOk())
        .andExpect(view().name(VIEW_NAME_INDEX))
        .andExpect(forwardedUrl(VIEW_PREFIX + VIEW_NAME_INDEX + VIEW_SUFFIX));
  }
}
