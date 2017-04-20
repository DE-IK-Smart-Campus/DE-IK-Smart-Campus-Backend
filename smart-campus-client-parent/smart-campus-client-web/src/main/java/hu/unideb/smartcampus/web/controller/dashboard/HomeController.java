package hu.unideb.smartcampus.web.controller.dashboard;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.unideb.smartcampus.service.api.authentication.SmartCampusUserDetails;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/home")
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  /**
   * TODO.
   */
  private static final String HOME_VIEW = "dashboard/home";
  /**
   * TODO.
   */
  private static final String CURRENT_USERNAME_MODEL_OBJECT_NAME = "currentUsername";
  /**
   * TODO.
   */
  private static final String IS_STAFF_MODEL_OBJECT_NAME = "isStaff";

  /**
   * TODO.
   * 
   * @return asd
   */
  @GetMapping
  public ModelAndView loadHomeView(final Principal principal) {
    final ModelAndView modelAndView = new ModelAndView(HOME_VIEW);
    final String name = principal.getName();
    UsernamePasswordAuthenticationToken details = (UsernamePasswordAuthenticationToken) principal;
    SmartCampusUserDetails userDetails = (SmartCampusUserDetails) details.getPrincipal();
    modelAndView.addObject(IS_STAFF_MODEL_OBJECT_NAME, userDetails.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_STAFF")));
    modelAndView.addObject(CURRENT_USERNAME_MODEL_OBJECT_NAME, name);
    return modelAndView;
  }
}
