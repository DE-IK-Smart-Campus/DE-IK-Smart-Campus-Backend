package hu.unideb.smartcampus.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

  private static final String ERROR_VIEW = "error";

  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ModelAndView defaultErrorHandler(Throwable e) {
    LOGGER.error("Error.", e);
    return new ModelAndView(ERROR_VIEW).addObject(e);
  }
}
