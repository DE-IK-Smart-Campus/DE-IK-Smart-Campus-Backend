package hu.unideb.smartcampus.service.api.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEntity;
import hu.unideb.smartcampus.persistence.repository.ConsultingDateRepository;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.ConsultingHourService;
import hu.unideb.smartcampus.service.api.OfficeHourGeneratorService;
import hu.unideb.smartcampus.service.api.converter.todomain.SubjectEntityToSubjectConverter;
import hu.unideb.smartcampus.service.api.converter.toentity.ConsultingDateToConsultingDateEntityConverter;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.Subject;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;

/**
 * Consulting hours service.
 *
 */
@Service
public class ConsultingHourServiceImpl implements ConsultingHourService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHourServiceImpl.class);

  /**
   * User repository.
   */
  private final UserRepository userRepository;

  /**
   * Instructor repository.
   */
  private final InstructorRepository instructorRepository;

  /**
   * Consulting date repository.
   */
  private final ConsultingDateRepository consultingDateRepository;

  /**
   * Office hour generator service.
   */
  private final OfficeHourGeneratorService officeHourGeneratorService;

  /**
   * Entity to domain converter.
   */
  private final SubjectEntityToSubjectConverter subjectConverter;

  /**
   * Consulting date domain to entity.
   */
  private final ConsultingDateToConsultingDateEntityConverter consultingDateConverter;

  /**
   * Constructs an instance of ConsultingHourServiceImpl.
   */
  @Autowired
  public ConsultingHourServiceImpl(UserRepository userRepository,
      SubjectEntityToSubjectConverter subjectConverter, InstructorRepository instructorRepository,
      ConsultingDateRepository consultingDateRepository,
      OfficeHourGeneratorService officeHourGeneratorService,
      ConsultingDateToConsultingDateEntityConverter consultingDateConverter) {
    this.userRepository = userRepository;
    this.subjectConverter = subjectConverter;
    this.instructorRepository = instructorRepository;
    this.consultingDateConverter = consultingDateConverter;
    this.consultingDateRepository = consultingDateRepository;
    this.officeHourGeneratorService = officeHourGeneratorService;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Transactional(readOnly = true)
  public Set<Subject> getSubjectsByUserId(final Long id) {
    Set<SubjectEntity> subjects = userRepository.getSubjectsByUserId(id);
    return subjects.stream().map(subjectConverter::convert).collect(Collectors.toSet());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Integer generateOfficeHoursForInstructor(final Long instructorId,
      final List<OfficeHour> officeHours, final OfficeHourIntervall intervall) {
    LOGGER.info("Creating office hours for instructor with ID:{}.", instructorId);
    InstructorEntity instructorEntity = instructorRepository.findOne(instructorId);
    List<ConsultingDate> generatedOfficeHours =
        officeHourGeneratorService.generateOfficeHoursInIntervall(intervall, officeHours);

    Collection<? extends ConsultingDateEntity> newDates = convert(generatedOfficeHours);
    List<? extends ConsultingDateEntity> savedDates = consultingDateRepository.save(newDates);

    LOGGER.info("Saving new office hours, size:{}.", newDates.size());
    instructorEntity.getConsultingDates().addAll(savedDates);

    LOGGER.info("Saving instructor.");
    instructorRepository.save(instructorEntity);
    return newDates.size();
  }

  private List<ConsultingDateEntity> convert(List<ConsultingDate> generatedOfficeHours) {
    return generatedOfficeHours.stream().map(consultingDateConverter::convert)
        .collect(Collectors.toList());
  }
}
