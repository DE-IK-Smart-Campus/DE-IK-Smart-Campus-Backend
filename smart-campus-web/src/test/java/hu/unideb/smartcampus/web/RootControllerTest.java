package hu.unideb.smartcampus.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.unideb.smartcampus.web.controller.RootController;

@RunWith(MockitoJUnitRunner.class)
public class RootControllerTest extends AbstractControllerTest {

  private static final String REQUEST_URL_EMPTY = "";
  private static final String REQUEST_URL_ROOT = "/";
  private static final String REDIRECT_URL_INDEX = "/index";

  @InjectMocks
  private RootController rootController;

  @Override
  protected Object[] controllerUnderTest() {
    return new Object[]{this.rootController};
  }

  @Test
  public void redirectToIndexPathShouldRedirectToIndexPathWhenRequestUrlIsAnEmptyString() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_EMPTY))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl(REDIRECT_URL_INDEX));
  }

  @Test
  public void redirectToIndexPathShouldRedirectToIndexPathWhenRequestUrlIsTheRoot() throws Exception {
    this.mockMvc.perform(get(REQUEST_URL_ROOT))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl(REDIRECT_URL_INDEX));
  }
}
