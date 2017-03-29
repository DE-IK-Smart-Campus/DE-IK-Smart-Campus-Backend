package hu.unideb.smartcampus.service.api.converter.todomain;

import com.google.common.collect.Sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.Instructor;

@Component
public class InstructorEntityToInstructorConverter implements Converter<InstructorEntity, Instructor> {

  private final Converter<SubjectDetailsEntity, SubjectDetails> subjectDetailsConverter;

  private final Converter<ConsultingDateEntity, ConsultingDate> consultingDateConverter;

  @Autowired
  public InstructorEntityToInstructorConverter(final Converter<SubjectDetailsEntity, SubjectDetails> subjectDetailsConverter,
                                               final Converter<ConsultingDateEntity, ConsultingDate> consultingDateConverter) {
    this.subjectDetailsConverter = subjectDetailsConverter;
    this.consultingDateConverter = consultingDateConverter;
  }

  @Override
  public Instructor convert(final InstructorEntity instructorEntity) {
    return instructorEntity == null ? null : convertInstructorEntityToInstructor(instructorEntity);
  }

  private Instructor convertInstructorEntityToInstructor(final InstructorEntity instructorEntity) {
    return Instructor.builder()
        .id(instructorEntity.getId())
        .name(instructorEntity.getName())
        .subjects(convertSubjectDetailsEntitySetToSubjectDetailsSet(instructorEntity.getSubjects()))
        .consultingDates(convertConsultingDateEntitySetToConsultingDateSet(instructorEntity.getConsultingDates()))
        .build();
  }

  private Set<SubjectDetails> convertSubjectDetailsEntitySetToSubjectDetailsSet(final Set<SubjectDetailsEntity> subjectDetailsEntitySet) {
    return subjectDetailsEntitySet == null ? Sets.newHashSet() : subjectDetailsEntitySet.stream()
        .map(subjectDetailsEntity -> subjectDetailsConverter.convert(subjectDetailsEntity))
        .collect(Collectors.toSet());
  }

  private Set<ConsultingDate> convertConsultingDateEntitySetToConsultingDateSet(final Set<ConsultingDateEntity> consultingDateEntitySet) {
    return consultingDateEntitySet == null ? Sets.newHashSet() : consultingDateEntitySet.stream()
        .map(consultingDate -> consultingDateConverter.convert(consultingDate))
        .collect(Collectors.toSet());
  }
}
