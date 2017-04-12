package hu.unideb.smartcampus.service.api.converter.toentity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.domain.CustomEvent;
import hu.unideb.smartcampus.service.api.domain.User;

@Component
public class UserToUserEntityConverter implements Converter<User, UserEntity> {

  private final Converter<SubjectDetails, SubjectDetailsEntity> subjectDetailsConverter;

  private final Converter<CustomEvent, CustomEventEntity> customEventConverter;

  private final Converter<CourseAppointment, CourseAppointmentEntity> courseConverter;

  @Autowired
  public UserToUserEntityConverter(
      final Converter<SubjectDetails, SubjectDetailsEntity> subjectDetailsConverter,
      final Converter<CustomEvent, CustomEventEntity> customEventConverter,
      final Converter<CourseAppointment, CourseAppointmentEntity> courseConverter) {
    this.subjectDetailsConverter = subjectDetailsConverter;
    this.customEventConverter = customEventConverter;
    this.courseConverter = courseConverter;
  }

  @Override
  public UserEntity convert(final User user) {
    return user == null ? null : convertUserToUserEntity(user);
  }

  private UserEntity convertUserToUserEntity(final User user) {
    return UserEntity.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .role(user.getRole())
        .fullName(user.getFullName())
        .neptunIdentifier(user.getNeptunIdentifier())
        .actualSubjects(
            convertSubjectDetailsSetToSubjectDetailsEntitySet(user.getSubjectDetailsList()))
        .mucChatList(user.getMucChatList())
        .singleChatList(user.getSingleChatList())
        .customEvents(convertCustomEventListToCustomEventEntityList(user.getCustomEventList()))
        .courseAppointments(convertToEntity(user.getCourseAppointmentList()))
        .build();
  }

  private Set<CourseAppointmentEntity> convertToEntity(
      List<CourseAppointment> courseAppointmentList) {
    return courseAppointmentList == null ? null : courseAppointmentList.stream()
        .map(customEvent -> courseConverter.convert(customEvent))
        .collect(Collectors.toSet());
  }

  private List<CustomEventEntity> convertCustomEventListToCustomEventEntityList(
      List<CustomEvent> customEventList) {
    return customEventList == null ? null : customEventList.stream()
        .map(customEvent -> customEventConverter.convert(customEvent))
        .collect(Collectors.toList());
  }

  private Set<SubjectDetailsEntity> convertSubjectDetailsSetToSubjectDetailsEntitySet(
      final List<SubjectDetails> subjectDetailsSet) {
    return subjectDetailsSet == null ? null : subjectDetailsSet.stream()
        .map(subjectDetails -> subjectDetailsConverter.convert(subjectDetails))
        .collect(Collectors.toSet());
  }
}
