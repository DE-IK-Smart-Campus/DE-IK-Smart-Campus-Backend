package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;

@Component
public class SubjectEventToSubjectEventEntityConverter
    implements Converter<SubjectEvent, SubjectEventEntity> {

  private final Converter<SubjectDetails, SubjectDetailsEntity> subjectDetailsConverter;

  @Autowired
  public SubjectEventToSubjectEventEntityConverter(
      final Converter<SubjectDetails, SubjectDetailsEntity> subjectDetailsConverter) {
    this.subjectDetailsConverter = subjectDetailsConverter;
  }

  @Override
  public SubjectEventEntity convert(final SubjectEvent subjectEvent) {
    return subjectEvent == null ? null : convertSubjectEventToSubjectEventEntity(subjectEvent);
  }

  private SubjectEventEntity convertSubjectEventToSubjectEventEntity(
      final SubjectEvent subjectEvent) {
    return SubjectEventEntity.builder()
        .id(subjectEvent.getId())
        .courseCode(subjectEvent.getCourseCode())
        .roomLocation(subjectEvent.getRoomLocation())
        .subjectDetailsEntity(
            convertSubjectDetailsToSubjectDetailsEntity(subjectEvent.getSubjectDetails()))
        .build();
  }

  private SubjectDetailsEntity convertSubjectDetailsToSubjectDetailsEntity(
      final SubjectDetails subjectDetails) {
    return subjectDetailsConverter.convert(subjectDetails);
  }

}
