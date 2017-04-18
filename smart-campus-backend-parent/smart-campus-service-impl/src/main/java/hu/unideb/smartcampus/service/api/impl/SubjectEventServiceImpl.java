package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

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
import hu.unideb.smartcampus.persistence.repository.CourseAppointmentRepository;
import hu.unideb.smartcampus.persistence.repository.SubjectEventRepository;
import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.InstructorService;
import hu.unideb.smartcampus.service.api.SubjectDetailsService;
import hu.unideb.smartcampus.service.api.SubjectEventService;
import hu.unideb.smartcampus.service.api.UserService;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.domain.Instructor;
import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.CourseInfoWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.StudentTimeTableInfo;
import hu.unideb.smartcampus.service.api.util.StudentCourseUtil;
import hu.unideb.smartcampus.shared.primarykey.SubjectDetailsPrimaryKey;
import hu.unideb.smartcampus.webservice.api.ejabberd.MultiUserChatService;
import hu.unideb.smartcampus.webservice.api.ejabberd.SharedRosterService;
import hu.unideb.smartcampus.webservice.api.neptun.StudentTimeTable;

@Service
public class SubjectEventServiceImpl implements SubjectEventService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SubjectEventServiceImpl.class);

  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  private String domain;

  private final SubjectEventRepository subjectEventRepository;

  private final SubjectDetailsService subjectDetailsService;

  private final ConversionService conversionService;

  private final InstructorService instructorService;

  private final UserService userService;

  private final CalendarService calendarService;

  private final StudentCourseUtil studentCourseUtil;

  private final CourseAppointmentRepository courseAppointmentRepository;

  /**
   */
  @Autowired
  public SubjectEventServiceImpl(SubjectEventRepository subjectEventRepository,
      SubjectDetailsService subjectDetailsService, ConversionService conversionService,
      InstructorService instructorService, UserService userService, CalendarService calendarService,
      SharedRosterService sharedRosterService,
      MultiUserChatService multiUserChatService,
      StudentCourseUtil studentCourseUtil,
      CourseAppointmentRepository courseAppointmentRepository) {
    this.subjectEventRepository = subjectEventRepository;
    this.subjectDetailsService = subjectDetailsService;
    this.conversionService = conversionService;
    this.instructorService = instructorService;
    this.userService = userService;
    this.calendarService = calendarService;
    this.studentCourseUtil = studentCourseUtil;
    this.courseAppointmentRepository = courseAppointmentRepository;
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
  public SubjectEvent save(final SubjectEvent subjectEvent) {
    Assert.notNull(subjectEvent);
    SubjectEventEntity savedEntity = subjectEventRepository
        .save(conversionService.convert(subjectEvent, SubjectEventEntity.class));
    return conversionService.convert(savedEntity, SubjectEvent.class);
  }

  @Transactional
  @Override
  public List<SubjectEvent> save(final List<SubjectEvent> subjectEvents) {
    Assert.notNull(subjectEvents);
    return subjectEvents.stream()
        .map(this::saveIfNotExists)
        .filter(event -> event != null)
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
  public List<SubjectEvent> saveSubjectEvents(
      final StudentTimeTable studentTimeTable,
      final String userName) throws IOException {

    CourseInfoWrapper courseInfo =
        studentCourseUtil.getCourseInfosByStudentTimeTable(studentTimeTable);
    courseInfo.setUsername(userName);

    final StudentTimeTableInfo result = calendarService.downloadStudentTimeTable(courseInfo);
    final User user = userService.getByUsername(userName).get();

    saveSubjectDetailsFromTimeTable(result);
    final List<SubjectEvent> savedEvents = save(result.getSubjectEvents());
    user.getSubjectEventList().addAll(savedEvents);
    calendarService.pairEventWithAppointementByStudent(courseInfo, user);

    userService.save(user);

    return result.getSubjectEvents();
  }

  private List<SubjectDetails> saveSubjectDetailsFromTimeTable(final StudentTimeTableInfo result) {
    List<SubjectDetails> subjectDetails = result.getSubjectDetails();
    return saveInstructorsFromDetails(subjectDetails);
  }

  private List<SubjectDetails> saveInstructorsFromDetails(List<SubjectDetails> subjectDetails) {
    List<SubjectDetails> saveSubjectDetails = saveSubjectDetails(subjectDetails);
    saveSubjectDetails
        .forEach(this::saveInstructorWithSubjectDetails);
    return saveSubjectDetails;
  }

  private List<SubjectDetails> saveSubjectDetails(List<SubjectDetails> subjectDetails) {
    return subjectDetailsService.save(subjectDetails);
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
  public SubjectEvent saveIfNotExists(SubjectEvent subjectEvent) {
    SubjectEventEntity eventEntity =
        conversionService.convert(subjectEvent, SubjectEventEntity.class);
    List<SubjectEventEntity> subjectEventEntityList =
        subjectEventRepository.findBySubjectDetailsEntityAndRoomLocationAndCourseCode(
            eventEntity.getSubjectDetailsEntity(), subjectEvent.getRoomLocation(),
            subjectEvent.getCourseCode());
    if (subjectEventEntityList.isEmpty()) {
      LOGGER.info("Saving {} with {} code.", subjectEvent.getSubjectDetails().getSubjectName(),
          subjectEvent.getCourseCode());
      return save(subjectEvent);
    } else {
      SubjectEventEntity subjectEventEntity = subjectEventEntityList.get(0);
      if (subjectEventEntity != null) {
        return conversionService.convert(subjectEventEntity, SubjectEvent.class);
      }
    }
    return null;
  }

  @Transactional(readOnly = true)
  @Override
  public List<CourseAppointment> getCourseAppointmentByUsernameAndSubjectEvent(String username,
      SubjectEvent subjectEvent) {
    return userService.getCourseAppointmentsByUsernameAndSubjectEvent(username, subjectEvent);
  }

  private SubjectDetailsPrimaryKey createKeyBySubjectDetails(SubjectDetails subjectDetails) {
    SubjectDetailsPrimaryKey key = new SubjectDetailsPrimaryKey();
    key.setSubjectName(subjectDetails.getSubjectName());
    key.setSubjectType(subjectDetails.getSubjectType().toString());
    key.setStartPeriod(subjectDetails.getStartPeriod());
    key.setEndPeriod(subjectDetails.getEndPeriod());
    return key;
  }
}
