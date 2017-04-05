package hu.unideb.smartcampus.service.api.converter.toiq;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.UserConsultingDateEntity;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * User consulting date to student IQ element.
 */
@Component
public class UserConsultingDateEntityToStudentIqElementConverter
    implements Converter<UserConsultingDateEntity, StudentIqElement> {

  @Override
  public StudentIqElement convert(UserConsultingDateEntity source) {
    return StudentIqElement.builder()
        .studentName(source.getUser().getFullName())
        // FIXME neptun kódot majd itt beállítani.
        // .neptunIdentifier("XXX")
        .duration(source.getDuration())
        .reason(source.getReason())
        .build();
  }

}
