package hu.unideb.smartcampus.service.api.converter.todomain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;

@Component
public class SubjectEventEntityToSubjectEventConverter
    implements Converter<SubjectEventEntity, SubjectEvent> {

  private final Converter<SubjectDetailsEntity, SubjectDetails> subjectDetailsConverter;

  @Autowired
  public SubjectEventEntityToSubjectEventConverter(
      final Converter<SubjectDetailsEntity, SubjectDetails> subjectDetailsConverter) {
    this.subjectDetailsConverter = subjectDetailsConverter;
  }

  @Override
  public SubjectEvent convert(final SubjectEventEntity subjectEventEntity) {
    return subjectEventEntity == null ? null
        : convertSubjectEventEntityToSubjectEvent(subjectEventEntity);
  }

  private SubjectEvent convertSubjectEventEntityToSubjectEvent(
      final SubjectEventEntity subjectEventEntity) {
    return SubjectEvent.builder()
        .id(subjectEventEntity.getId())
        .roomLocation(subjectEventEntity.getRoomLocation())
        .courseCode(subjectEventEntity.getCourseCode())
        .subjectDetails(convertSubjectDetailsEntityToSubjectDetails(
            subjectEventEntity.getSubjectDetailsEntity()))
        .build();
  }

  private SubjectDetails convertSubjectDetailsEntityToSubjectDetails(
      final SubjectDetailsEntity subjectDetailsEntity) {
    return subjectDetailsConverter.convert(subjectDetailsEntity);
  }

}
