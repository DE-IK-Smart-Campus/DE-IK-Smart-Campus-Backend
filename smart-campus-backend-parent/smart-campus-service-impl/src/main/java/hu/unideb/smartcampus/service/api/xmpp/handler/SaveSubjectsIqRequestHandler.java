package hu.unideb.smartcampus.service.api.xmpp.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.exception.InputParseException;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SaveSubjectsIcsIqRequest;


/**
 * Save subjects IQ request handler.
 */
@Service
public class SaveSubjectsIqRequestHandler extends AbstractSmartCampusIqRequestHandler {

  /**
   * Response on success.
   */
  protected static final String SUCCESS_MESSAGE = "ICS file parsed and subjects were saved.";

  /**
   * Response on parse error.
   */
  protected static final String ERROR_MESSAGE = "Could not parse ICS file.";

  /**
   * Class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SaveSubjectsIqRequestHandler.class);

  @Autowired
  private SubjectEventService subjectEventService;

  @Autowired
  private UserService userService;

  @Autowired
  private CalendarService calendarService;

  /**
   * Ctor.
   */
  public SaveSubjectsIqRequestHandler() {
    super(SaveSubjectsIcsIqRequest.ELEMENT, BaseSmartCampusIqRequest.BASE_NAMESPACE, Type.set,
        Mode.async);
  }

  /**
   * Ctor.
   */
  protected SaveSubjectsIqRequestHandler(String element, String namespace, Type type, Mode mode) {
    super(element, namespace, type, mode);
  }

  /**
   * Handling request.
   */
  @Override
  public IQ handleIQRequest(IQ iqRequest) {
    SaveSubjectsIcsIqRequest iq = (SaveSubjectsIcsIqRequest) super.handleIQRequest(iqRequest);
    List<SubjectEvent> result;
    try {
      result = downloadCalendarAndSave(iq);
    } catch (InputParseException e) {
      LOGGER.error("Error while parsing ICS file.", e);
      iq.setStatusMessage(ERROR_MESSAGE);
      return iq;
    }
    pairStudentWithSubjects(iq, result);
    iq.setStatusMessage(SUCCESS_MESSAGE);
    return iq;
  }

  private void pairStudentWithSubjects(SaveSubjectsIcsIqRequest iq, List<SubjectEvent> result) {
    final User user = userService.getByUsername(iq.getStudent()).get();
    user.getSubjectDetailsList().addAll(result.stream()
        .map(subjectEvent -> subjectEvent.getSubjectDetails()).collect(Collectors.toList()));
    userService.save(user);
  }

  private List<SubjectEvent> downloadCalendarAndSave(SaveSubjectsIcsIqRequest iq)
      throws InputParseException {
    List<SubjectEvent> result;
    result = calendarService.downloadCalendar(iq.getIcs());
    subjectEventService.save(result);
    return result;
  }

}
