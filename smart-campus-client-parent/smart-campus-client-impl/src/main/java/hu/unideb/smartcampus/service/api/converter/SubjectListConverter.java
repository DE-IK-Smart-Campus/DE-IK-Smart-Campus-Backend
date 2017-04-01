package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.Subject;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

public class SubjectListConverter implements Converter<List<SubjectIqElement>, List<Subject>> {

  private Converter<SubjectIqElement, Subject> subjectConverter;

  public SubjectListConverter() {
    this.subjectConverter = new SubjectConverter();
  }

  @Override
  @Deprecated
  public List<Subject> convert(List<SubjectIqElement> subjectIqElements) {
    return subjectIqElements.stream().map(
        subjectIqElement -> subjectConverter.convert(subjectIqElement)
    ).collect(Collectors.toList());
  }
}
