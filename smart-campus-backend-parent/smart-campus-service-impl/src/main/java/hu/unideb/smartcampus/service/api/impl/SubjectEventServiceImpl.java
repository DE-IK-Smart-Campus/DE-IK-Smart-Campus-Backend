package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.persistence.repository.SubjectEventRepository;
import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.InstructorService;
import hu.unideb.smartcampus.service.api.SubjectDetailsService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.Instructor;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.StudentTimeTableInfo;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunEndpointService;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

@Service
public class SubjectEventServiceImpl implements SubjectEventService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SubjectEventServiceImpl.class);

  private final SubjectEventRepository subjectEventRepository;

  private final SubjectDetailsService subjectDetailsService;

  private final ConversionService conversionService;

  private final InstructorService instructorService;

  private final UserService userService;

  private final CalendarService calendarService;

  private final NeptunEndpointService neptun;

  /**
   */
  @Autowired
  public SubjectEventServiceImpl(SubjectEventRepository subjectEventRepository,
      SubjectDetailsService subjectDetailsService, ConversionService conversionService,
      InstructorService instructorService,
      UserService userService, CalendarService calendarService, NeptunEndpointService neptun) {
    this.subjectEventRepository = subjectEventRepository;
    this.subjectDetailsService = subjectDetailsService;
    this.conversionService = conversionService;
    this.instructorService = instructorService;
    this.userService = userService;
    this.calendarService = calendarService;
    this.neptun = neptun;
  }

  @Transactional(readOnly = true)
  @Override
  public List<SubjectEvent> getAllSubjectEventByUserId(final Long userId) {
    final List<SubjectDetails> subjectDetailsList =
        subjectDetailsService.getAllSubjectDetailsByUserId(userId);
    return getAllSubjectEventBySubjectDetails(subjectDetailsList);
  }

  @Transactional(readOnly = true)
  @Override
  public List<SubjectEvent> getAllSubjectEventBySubjectDetails(
      final List<SubjectDetails> subjectDetailsList) {
    Assert.notNull(subjectDetailsList);

    final Set<SubjectDetailsEntity> subjectDetailsEntities = subjectDetailsList.stream()
        .map(
            subjectDetails -> conversionService.convert(subjectDetails, SubjectDetailsEntity.class))
        .collect(Collectors.toSet());

    final List<SubjectEventEntity> subjectEventEntities =
        subjectEventRepository.findAllBySubjectDetailsEntityIn(subjectDetailsEntities);

    return subjectEventEntities.stream()
        .map(
            subjectEventEntity -> conversionService.convert(subjectEventEntity, SubjectEvent.class))
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public void save(final SubjectEvent subjectEvent) {
    Assert.notNull(subjectEvent);

    subjectEventRepository.save(conversionService.convert(subjectEvent, SubjectEventEntity.class));
  }

  @Transactional
  @Override
  public void save(final List<SubjectEvent> subjectEvents) {
    Assert.notNull(subjectEvents);

    preSavingSubjectDetails(mapSubjectEventListToSubjectDetailsList(subjectEvents));

    subjectEvents.forEach(subjectEvent -> saveIfNotExists(subjectEvent));
    subjectEventRepository.flush();
  }

  private void preSavingSubjectDetails(final List<SubjectDetails> subjectDetailsList) {
    subjectDetailsService.save(subjectDetailsList);
  }

  private List<SubjectDetails> mapSubjectEventListToSubjectDetailsList(
      final List<SubjectEvent> subjectEvents) {
    return subjectEvents.stream()
        .map(subjectEvent -> subjectEvent.getSubjectDetails())
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<SubjectEvent> getAllSubjectEventByUsername(String username) {
    final List<SubjectDetails> subjectDetails =
        subjectDetailsService.getAllSubjectDetailsByUsername(username);
    return getAllSubjectEventBySubjectDetails(subjectDetails);
  }

  @Transactional(readOnly = true)
  @Override
  public List<SubjectEvent> getSubjectEventWithinRangeByUsername(String username, LocalDate from,
      LocalDate to) {
    List<SubjectDetails> subjectDetails =
        subjectDetailsService.getSubjectDetailsWithinRangeByUsername(from, to, username);
    return getAllSubjectEventBySubjectDetails(subjectDetails);
  }

  @Transactional
  @Override
  public void saveSubjectEvents(
      final String neptunIdentifier,
      final String userName) throws IOException {
    StudentTimeTable studentTimetable = neptun.getStudentTimetable(neptunIdentifier);
    final StudentTimeTableInfo result = calendarService.downloadStudentTimeTable(studentTimetable);
    save(result.getSubjectEvents());

    result.getSubjectEvents().forEach(
        subjectEvent -> this.saveInstructorWithSubjectDetails(subjectEvent.getSubjectDetails()));

    final User user = userService.getByUsername(userName).get();
    user.getSubjectDetailsList()
        .addAll(result.getSubjectEvents()
            .stream()
            .map(subjectEvent -> subjectEvent.getSubjectDetails())
            .collect(Collectors.toList()));
    user.getCourseAppointmentList().addAll(result.getCourseAppointments());

    userService.save(user);
  }

  private void saveInstructorWithSubjectDetails(final SubjectDetails subjectDetails) {
    subjectDetails.getInstructors().stream().filter(p -> p.getNeptunIdentifier() != null)
        .forEach(instr -> {
          Instructor instructor;
          final Optional<Instructor> instructorOptional =
              this.instructorService.getInstructorByNeptunIdentifier(instr.getNeptunIdentifier());
          if (instructorOptional.isPresent()) {
            instructor = instructorOptional.get();
            instructor.getSubjects().add(subjectDetails);
          } else {
            LOGGER.info("Creating new instructor with name:{}", instr.getName());
            instructor = Instructor.builder()
                .name(instr.getName())
                .neptunIdentifier(instr.getNeptunIdentifier())
                .subjects(Sets.newHashSet(subjectDetails))
                .build();
          }
          this.instructorService.saveInstructor(instructor);
        });
  }

  @Override
  public void saveIfNotExists(SubjectEvent subjectEvent) {
    SubjectEventEntity eventEntity =
        conversionService.convert(subjectEvent, SubjectEventEntity.class);
    List<SubjectEventEntity> findBySubjectDetailsEntity =
        subjectEventRepository.findBySubjectDetailsEntityAndRoomLocation(
            eventEntity.getSubjectDetailsEntity(), subjectEvent.getRoomLocation());
    if (findBySubjectDetailsEntity.isEmpty()) {
      save(subjectEvent);
    }
  }
}
