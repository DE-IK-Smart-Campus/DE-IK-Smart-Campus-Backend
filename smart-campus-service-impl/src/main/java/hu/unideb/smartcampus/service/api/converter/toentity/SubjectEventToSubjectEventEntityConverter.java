package hu.unideb.smartcampus.service.api.converter.toentity;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.persistence.entity.AppointmentTimeEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;

@Component
public class SubjectEventToSubjectEventEntityConverter implements Converter<SubjectEvent, SubjectEventEntity> {

  private final ConversionService conversionService;

  @Autowired
  public SubjectEventToSubjectEventEntityConverter(final ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  @Override
  public SubjectEventEntity convert(final SubjectEvent subjectEvent) {
    return subjectEvent == null ? null : convertSubjectEventToSubjectEventEntity(subjectEvent);
  }

  private SubjectEventEntity convertSubjectEventToSubjectEventEntity(final SubjectEvent subjectEvent) {
    return SubjectEventEntity.builder()
        .id(subjectEvent.getId())
        .roomLocation(subjectEvent.getRoomLocation())
        .subjectDetailsEntity(convertSubjectDetailsToSubjectDetailsEntity(subjectEvent.getSubjectDetails()))
        .appointmentTimes(convertAppointmentTimeListToAppointmentTimeEntityList(subjectEvent.getAppointmentTimeList()))
        .build();
  }

  private SubjectDetailsEntity convertSubjectDetailsToSubjectDetailsEntity(final SubjectDetails subjectDetails) {
    return conversionService.convert(subjectDetails, SubjectDetailsEntity.class);
  }

  private List<AppointmentTimeEntity> convertAppointmentTimeListToAppointmentTimeEntityList(final List<AppointmentTime> appointmentTimeList) {
    return appointmentTimeList == null ? null : appointmentTimeList.parallelStream()
        .map(appointmentTime -> conversionService.convert(appointmentTime, AppointmentTimeEntity.class))
        .collect(Collectors.toList());
  }
}
