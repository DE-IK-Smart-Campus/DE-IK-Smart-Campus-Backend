package hu.unideb.smartcampus.service.api.converter.toentity;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;

@Component
public class CourseAppointmentToCourseAppointmentEntityConverter
    implements Converter<CourseAppointment, CourseAppointmentEntity> {


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
        .build();
  }

}
