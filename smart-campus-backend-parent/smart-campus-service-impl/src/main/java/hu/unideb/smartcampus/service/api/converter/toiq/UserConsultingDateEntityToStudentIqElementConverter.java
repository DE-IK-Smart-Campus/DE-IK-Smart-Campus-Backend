package hu.unideb.smartcampus.service.api.converter.toiq;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;

/**
 * User consulting date to student IQ element.
 */
@Component
public class UserConsultingDateEntityToStudentIqElementConverter
    implements Converter<UserConsultingDateEntity, InstructorConsultingDateIqElement> {

  @Override
  public InstructorConsultingDateIqElement convert(UserConsultingDateEntity source) {
    return InstructorConsultingDateIqElement.builder()
        // FIXME neptun kódot majd itt beállítani.
        // .neptunIdentifier("XXX")
        .build();
  }

}
