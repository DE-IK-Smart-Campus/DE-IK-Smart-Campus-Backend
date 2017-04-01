package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.domain.Subject;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

public class SubjectConverter implements Converter<SubjectIqElement, Subject> {

  private Converter<List<InstructorIqElement>, List<Instructor>> instructorListConverter;

  public SubjectConverter() {
    this.instructorListConverter = new InstructorListConverter();
  }

  @Override
  @Deprecated
  public Subject convert(SubjectIqElement subjectIqElement) {
    return new Subject(subjectIqElement.getSubjectName(), instructorListConverter.convert(subjectIqElement.getInstructors()));
  }
}
