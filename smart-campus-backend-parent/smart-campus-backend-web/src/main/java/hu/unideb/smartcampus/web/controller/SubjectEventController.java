package hu.unideb.smartcampus.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.InstructorService;
import hu.unideb.smartcampus.service.api.SubjectDetailsService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.Instructor;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.exception.InputParseException;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunEndpointService;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;


@RestController
public class SubjectEventController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SubjectEventController.class);

  @Autowired
  private SubjectEventService subjectEventService;

  @Autowired
  private SubjectDetailsService subjectDetailsService;

  @Autowired
  private InstructorService instructorService;

  @Autowired
  private UserService userService;

  @Autowired
  private CalendarService calendarService;

  @Autowired
  private NeptunEndpointService neptun;

  @GetMapping(path = "/save/subjectEvents")
  public ResponseEntity<Void> saveSubjectEvents(
      @RequestParam(name = "neptunIdentifier") final String neptunIdentifier,
      @RequestParam(name = "user") final String userName) throws IOException, InputParseException {
    StudentTimeTable studentTimetable = neptun.getStudentTimetable(neptunIdentifier);
    final List<SubjectEvent> result = calendarService.downloadStudentTimeTable(studentTimetable);
    subjectEventService.save(result);

    result.forEach(
        subjectEvent -> this.saveInstructorWithSubjectDetails(subjectEvent.getSubjectDetails()));

    final User user = userService.getByUsername(userName).get();
    user.getSubjectDetailsList().addAll(result.parallelStream()
        .map(subjectEvent -> subjectEvent.getSubjectDetails()).collect(Collectors.toList()));

    userService.save(user);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/list/subjectEvents")
  public ResponseEntity<List<SubjectEvent>> getSubjectEventsByUserId(final Principal principal) {
    final User user = userService.getByUsername(principal.getName()).orElse(null);
    return ResponseEntity.ok(subjectEventService.getAllSubjectEventByUserId(user.getId()));
  }

  @GetMapping(path = "/list/subjectDetails")
  public ResponseEntity<List<SubjectDetails>> getSubjectDetailsByUserId(final Principal principal) {
    final User user = userService.getByUsername(principal.getName()).orElse(null);
    return ResponseEntity.ok(subjectDetailsService.getAllSubjectDetailsByUserId(user.getId()));
  }

  @GetMapping(path = "/list/instructors")
  public ResponseEntity<List<Instructor>> getAllInstructor() {
    return ResponseEntity.ok(this.instructorService.getAllInstructor());
  }

  private void saveInstructorWithSubjectDetails(final SubjectDetails subjectDetails) {
    subjectDetails.getInstructors().stream().filter(p -> p.getNeptunIdentifier() != null)
        .forEach(instr -> {
          Instructor instructor;
          final Optional<Instructor> instructorOptional =
              this.instructorService.getInstructorByNeptunIdentifier(instr.getNeptunIdentifier());
          if (instructorOptional.isPresent()) {
            instructor = instructorOptional.get();
            LOGGER.info("Instructor add {} to subjects:{}", subjectDetails,
                instructor.getSubjects());
            instructor.getSubjects().add(subjectDetails);
          } else {
            LOGGER.info("Creating new instructor with name:{}", instr.getName());
            instructor = Instructor.builder()
                .name(instr.getName())
                .neptunIdentifier(instr.getNeptunIdentifier())
                .build();
          }
          this.instructorService.saveInstructor(instructor);
        });
  }
}
