package hu.unideb.smartcampus.service.api.converter.toentity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;

@Component
public class CourseAppointmentToCourseAppointmentEntityConverter
    implements Converter<CourseAppointment, CourseAppointmentEntity> {


  private final Converter<SubjectEvent, SubjectEventEntity> subjectEventEntityConverter;

  /**
   * Consturctor.
   */
  @Autowired
  public CourseAppointmentToCourseAppointmentEntityConverter(
      Converter<SubjectEvent, SubjectEventEntity> subjectEventEntityConverter) {
    this.subjectEventEntityConverter = subjectEventEntityConverter;
  }

  @Override
  public CourseAppointmentEntity convert(final CourseAppointment userEntity) {
    return userEntity == null ? null : convertUserEntityToUser(userEntity);
  }

  private CourseAppointmentEntity convertUserEntityToUser(final CourseAppointment domain) {
    return CourseAppointmentEntity.builder()
        .id(domain.getId())
        .startDate(domain.getStartDate())
        .endDate(domain.getEndDate())
        .wasPresent(domain.getWasPresent())
        .courseCode(domain.getCourseCode())
        .subjectEvent(subjectEventEntityConverter.convert(domain.getSubjectEvent()))
        .build();
  }

}
