package hu.unideb.smartcampus.service.api.converter.toentity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;
import hu.unideb.smartcampus.service.api.domain.CustomEvent;
import hu.unideb.smartcampus.service.api.domain.User;

@Component
public class UserToUserEntityConverter implements Converter<User, UserEntity> {

  private final Converter<SubjectEvent, SubjectEventEntity> subjectEventConverter;

  private final Converter<CustomEvent, CustomEventEntity> customEventConverter;

  private final Converter<CourseAppointment, CourseAppointmentEntity> courseConverter;

  @Autowired
  public UserToUserEntityConverter(
      final Converter<SubjectEvent, SubjectEventEntity> subjectEventConverter,
      final Converter<CustomEvent, CustomEventEntity> customEventConverter,
      final Converter<CourseAppointment, CourseAppointmentEntity> courseConverter) {
    this.subjectEventConverter = subjectEventConverter;
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
        .actualEvents(
            convertSubjectDetailsSetToSubjectDetailsEntitySet(user.getSubjectEventList()))
        .mucChatList(toSet(user.getMucChatList()))
        .singleChatList(toSet(user.getSingleChatList()))
        .customEvents(convertCustomEventListToCustomEventEntityList(user.getCustomEventList()))
        .courseAppointments(convertToEntity(user.getCourseAppointmentList()))
        .build();
  }

  private HashSet<String> toSet(List<String> list) {
    if (list == null)
      return new HashSet<>();
    return new HashSet<>(list);
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

  private Set<SubjectEventEntity> convertSubjectDetailsSetToSubjectDetailsEntitySet(
      final List<SubjectEvent> list) {
    return list == null ? null : list.stream()
        .map(subjectEvent -> subjectEventConverter.convert(subjectEvent))
        .collect(Collectors.toSet());
  }
}
