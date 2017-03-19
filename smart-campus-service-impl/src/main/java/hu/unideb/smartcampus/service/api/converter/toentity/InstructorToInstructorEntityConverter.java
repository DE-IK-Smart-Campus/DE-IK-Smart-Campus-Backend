package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.Instructor;

@Component
public class InstructorToInstructorEntityConverter implements Converter<Instructor, InstructorEntity> {

  private final ConversionService conversionService;

  @Autowired
  public InstructorToInstructorEntityConverter(final ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  @Override
  public InstructorEntity convert(final Instructor instructor) {
    return instructor == null ? null : convertInstructorToInstructorEntity(instructor);
  }

  private InstructorEntity convertInstructorToInstructorEntity(final Instructor instructor) {
    return InstructorEntity.builder()
        .id(instructor.getId())
        .name(instructor.getName())
        .subjects(convertSubjectDetailsSetToSubjectDetailsEntitySet(instructor.getSubjects()))
        .consultingDates(convertConsultingDateSetToConsultingDateEntitySet(instructor.getConsultingDates()))
        .build();
  }

  private Set<SubjectDetailsEntity> convertSubjectDetailsSetToSubjectDetailsEntitySet(final Set<SubjectDetails> subjectDetailsSet) {
    return subjectDetailsSet == null ? null : subjectDetailsSet.parallelStream()
        .map(subjectDetails -> conversionService.convert(subjectDetails, SubjectDetailsEntity.class))
        .collect(Collectors.toSet());
  }

  private Set<ConsultingDateEntity> convertConsultingDateSetToConsultingDateEntitySet(final Set<ConsultingDate> consultingDateSet) {
    return consultingDateSet == null ? null : consultingDateSet.parallelStream()
        .map(consultingDate -> conversionService.convert(consultingDate, ConsultingDateEntity.class))
        .collect(Collectors.toSet());
  }
}
