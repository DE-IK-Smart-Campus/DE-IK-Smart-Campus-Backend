package hu.unideb.smartcampus.service.api.converter.toentity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.InstructorEmbedded;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.InstructorWrapper;

@Component
public class SubjectDetailsToSubjectDetailsEntityConverter
    implements Converter<SubjectDetails, SubjectDetailsEntity> {

  @Override
  public SubjectDetailsEntity convert(final SubjectDetails subjectDetails) {
    return subjectDetails == null ? null
        : convertSubjectDetailsToSubjectDetailsEntity(subjectDetails);
  }

  private SubjectDetailsEntity convertSubjectDetailsToSubjectDetailsEntity(
      final SubjectDetails subjectDetails) {
    return SubjectDetailsEntity.builder()
        .subjectName(subjectDetails.getSubjectName())
        .subjectType(subjectDetails.getSubjectType().name())
        .startPeriod(subjectDetails.getStartPeriod())
        .endPeriod(subjectDetails.getEndPeriod())
        .instructors(toEmbeddedEntity(subjectDetails.getInstructors()))
        .build();
  }

  private List<InstructorEmbedded> toEmbeddedEntity(List<InstructorWrapper> instructors) {
    return instructors.stream().map(this::toEmbedded).collect(Collectors.toList());
  }

  private InstructorEmbedded toEmbedded(InstructorWrapper instructor) {
    return InstructorEmbedded.builder()
        .name(instructor.getName())
        .neptunIdentifier(instructor.getNeptunIdentifier())
        .build();
  }
}
