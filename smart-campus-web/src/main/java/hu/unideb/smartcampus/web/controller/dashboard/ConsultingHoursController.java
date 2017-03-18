package hu.unideb.smartcampus.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.SubjectRetrievalResponseWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.InstructorWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.SubjectWrapper;

/**
 * TODO.
 */
@Controller
@RequestMapping(path = "/dashboard/consulting-hours")
public class ConsultingHoursController {

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
    final ModelAndView modelAndView = new ModelAndView(CONSULTING_HOURS_VIEW);
    final SubjectRetrievalResponseWrapper mock = mockSubjectRetrievalResponseWrapper();
    modelAndView.addObject(SUBJECT_RETRIEVAL_RESPONSE_MODEL_OBJECT_NAME, mock);
    System.out.println(mock);
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
