package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;

// Deprecated, because currently the API does not get consulting dates from anywhere, but we do not need it right now
@Deprecated
public class InstructorConverter implements Converter<InstructorIqElement, Instructor> {

  @Override
  @Deprecated
  public Instructor convert(InstructorIqElement instructorIqElement) {
    return new Instructor(
        instructorIqElement.getInstructorId(),
        instructorIqElement.getName(),
        null);
  }
}
