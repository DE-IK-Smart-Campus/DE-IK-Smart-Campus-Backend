package hu.unideb.smartcampus.service.api.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;

public class InstructorListConverter implements Converter<List<InstructorIqElement>, List<Instructor>>{

  private Converter<InstructorIqElement, Instructor> instructorConverter;

  public InstructorListConverter() {
    this.instructorConverter = new InstructorConverter();
  }

  @Override
  public List<Instructor> convert(List<InstructorIqElement> instructorIqElements) {
    if(instructorIqElements == null)
      return new ArrayList<>();
    return instructorIqElements.stream().map(
        instructorIqElement -> instructorConverter.convert(instructorIqElement)
    ).collect(Collectors.toList());
  }
}
