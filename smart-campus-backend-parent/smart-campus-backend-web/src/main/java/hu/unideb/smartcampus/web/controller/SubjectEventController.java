package hu.unideb.smartcampus.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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


@RestController
public class SubjectEventController {

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

  @PostMapping(path = "/save/subjectEvents")
  public ResponseEntity<Void> saveSubjectEvents(@RequestParam(name = "url") final String url, final Principal principal) throws IOException, InputParseException {
    final List<SubjectEvent> result = calendarService.downloadCalendar(url);
    subjectEventService.save(result);

    result.forEach(subjectEvent -> this.saveInstructorWithSubjectDetails(subjectEvent.getSubjectDetails()));

    final User user = userService.getByUsername(principal.getName()).get();
    user.getSubjectDetailsList().addAll(result.parallelStream().map(subjectEvent -> subjectEvent.getSubjectDetails()).collect(Collectors.toList()));

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
    subjectDetails.getTeacherNames()
        .forEach(instructorName -> {
          Instructor instructor;
          final Optional<Instructor> instructorOptional = this.instructorService.getInstructorByName(instructorName);
          if (instructorOptional.isPresent()) {
             instructor = instructorOptional.get();
             instructor.getSubjects().add(subjectDetails);
          } else {
            instructor = Instructor.builder()
                .name(instructorName)
                .build();
          }
          this.instructorService.saveInstructor(instructor);
        });
  }
}
