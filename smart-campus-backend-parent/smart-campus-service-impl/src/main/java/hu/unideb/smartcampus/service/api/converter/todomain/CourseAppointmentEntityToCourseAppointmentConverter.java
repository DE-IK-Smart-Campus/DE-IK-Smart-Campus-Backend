package hu.unideb.smartcampus.service.api.converter.todomain;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.service.api.domain.CourseAppointment;

@Component
public class CourseAppointmentEntityToCourseAppointmentConverter
    implements Converter<CourseAppointmentEntity, CourseAppointment> {


  @Override
  public CourseAppointment convert(final CourseAppointmentEntity userEntity) {
    return userEntity == null ? null : convertUserEntityToUser(userEntity);
  }

  private CourseAppointment convertUserEntityToUser(final CourseAppointmentEntity entity) {
    return CourseAppointment.builder()
        .id(entity.getId())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .wasPresent(entity.getWasPresent())
        .courseCode(entity.getCourseCode())
        .build();
  }

}
