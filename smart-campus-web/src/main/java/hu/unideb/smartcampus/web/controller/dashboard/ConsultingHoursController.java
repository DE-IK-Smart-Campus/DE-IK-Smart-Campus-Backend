package hu.unideb.smartcampus.web.controller.dashboard;

import org.jivesoftware.smack.chat.ChatManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectRetrievalResponseWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.InstructorWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.SubjectWrapper;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.web.listener.ConsultingHoursChatManagerListener;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/consulting-hours")
public class ConsultingHoursController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHoursController.class);

  @Autowired
  private EjabberdUser user;

  private ChatManager chatManager;

  private SubjectRetrievalResponseWrapper result;

  /**
   * TODO.
   */
  private static final String CONSULTING_HOURS_VIEW = "dashboard/consulting-hours";

  /**
   * TODO.
   */
  private static final String SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME = "subjectRetrievalResponse";

  /**
   * TODO.
   * @return asd
   */
  @GetMapping
  public ModelAndView loadConsultingHoursView() {
    chatManager = ChatManager.getInstanceFor(user.getConnection());
    chatManager.addChatListener(new ConsultingHoursChatManagerListener(result));
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_VIEW);
    Integer i = 0;
    while (result == null && i < 60) {
      LOGGER.info("Result still null.");
      try {
        Thread.sleep(1000);
        i++;
      } catch (InterruptedException e) {
        LOGGER.error("Error on thread sleep.",e);
      }
    }
    LOGGER.info("Result {}.",result);
    modelAndView.addObject(SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME, result);
    return modelAndView;
  }

  /**
   * TODO.
   * @return asd
   */
  public SubjectRetrievalResponseWrapper mockSubjectRetrievalResponseWrapper() {
    return new SubjectRetrievalResponseWrapper(
        "AskSubjectsProcessMessageResponse",
        Arrays.asList(
            new SubjectWrapper(
                "Hálózati architektúrák és protokollok",
                Arrays.asList(
                    new InstructorWrapper(
                        1L,
                        "Dr. Gál Zoltán"
                    ),
                    new InstructorWrapper(
                        2L,
                        "Dr. Szilágyi Szabolcs"
                    ),
                    new InstructorWrapper(
                        3L,
                        "Vas Ádám"
                    )
                )
            ),
            new SubjectWrapper(
                "Az internet eszközei és szolgáltatásai",
                Arrays.asList(
                    new InstructorWrapper(
                        4L,
                        "Dr. Jeszenszky Péter"
                    )
                )
            ),
            new SubjectWrapper(
                "A mesterséges intelligencia alapjai",
                Arrays.asList(
                    new InstructorWrapper(
                        5L,
                        "Dr. Várterész Magdolna"
                    ),
                    new InstructorWrapper(
                        6L,
                        "Dr. Horváth Géza"
                    )
                )
            )
        ));
  }
}
