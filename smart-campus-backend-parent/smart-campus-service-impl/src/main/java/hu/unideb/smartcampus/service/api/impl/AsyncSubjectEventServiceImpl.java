package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.service.api.AsyncSubjectEventService;
import hu.unideb.smartcampus.service.api.SrgMucFacade;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.UserChatService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunEndpointService;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

/**
 * Service for delegate subject saving with Async.
 */
@Service
public class AsyncSubjectEventServiceImpl implements AsyncSubjectEventService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSubjectEventServiceImpl.class);

  private SubjectEventService subjectEventService;

  private final NeptunEndpointService neptun;

  private final SrgMucFacade srgMucFacade;

  private final UserChatService userChatService;

  /**
   * Constructor.
   */
  @Autowired
  public AsyncSubjectEventServiceImpl(SubjectEventService subjectEventService,
      NeptunEndpointService neptun,
      SrgMucFacade srgMucFacade,
      UserChatService userChatService) {
    this.neptun = neptun;
    this.srgMucFacade = srgMucFacade;
    this.userChatService = userChatService;
    this.subjectEventService = subjectEventService;
  }


  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  @Async
  public void saveSubjectEventsAsync(final String neptunIdentifier, final String userName)
      throws IOException {
    LOGGER.debug("Saving user subjects within an async call.");
    StudentTimeTable studentTimetable = neptun.getStudentTimetable(neptunIdentifier);
    List<SubjectEvent> subjectEvents =
        subjectEventService.saveSubjectEvents(studentTimetable, userName);
    generateSrgAsync(userName, subjectEvents);
    generateMucRoomsAndAddUserToRooms(userName, subjectEvents);
  }


  private void generateSrgAsync(final String userName, List<SubjectEvent> subjectEvents) {
    srgMucFacade.generateSrgAsync(subjectEvents, userName);
  }


  private void generateMucRoomsAndAddUserToRooms(final String userName,
      List<SubjectEvent> subjectEvents) {
    List<String> mucList = srgMucFacade.generateMuc(subjectEvents, userName);
    mucList.stream().forEach(mucJid -> userChatService.addMucToUser(userName, mucJid));
  }

}
