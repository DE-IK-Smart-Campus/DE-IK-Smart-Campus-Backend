package hu.unideb.smartcampus.service.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.service.api.InstructorService;
import hu.unideb.smartcampus.service.api.domain.Instructor;


@Service
public class InstructorServiceImpl implements InstructorService {

  private static final Logger LOGGER = LoggerFactory.getLogger(InstructorServiceImpl.class);

  private final InstructorRepository instructorRepository;
  private final ConversionService conversionService;

  @Autowired
  public InstructorServiceImpl(final InstructorRepository instructorRepository,
      final ConversionService conversionService) {
    this.instructorRepository = instructorRepository;
    this.conversionService = conversionService;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Instructor> getInstructorByName(final String name) {
    Assert.notNull(name);

    return Optional.ofNullable(this.conversionService
        .convert(this.instructorRepository.findByName(name), Instructor.class));
  }

  @Transactional(readOnly = true)
  @Override
  public List<Instructor> getAllInstructor() {
    return this.instructorRepository.findAll()
        .stream()
        .map(instructorEntity -> this.conversionService.convert(instructorEntity, Instructor.class))
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public void saveInstructor(final Instructor instructor) {
    Assert.notNull(instructor);
    LOGGER.info("Saving instructor: {}", instructor.getName());
    if (instructor.getNeptunIdentifier() == null) {
      LOGGER.info("Instructor has no NEPTUN ID, returning.");
      return;
    }
    InstructorEntity entity = this.conversionService.convert(instructor, InstructorEntity.class);
    LOGGER.info("Has instructor subjects:{}", !entity.getSubjects().isEmpty());
    LOGGER.info("Subject:{}", entity.getSubjects());
    LOGGER.info("Has instructor neptun identifier:{}", entity.getNeptunIdentifier() != null);
    if (entity.getNeptunIdentifier() != null) {
      this.instructorRepository
          .save(entity);
    }
  }

  @Override
  public Optional<Instructor> getInstructorByNeptunIdentifier(String neptunIdentifier) {
    Assert.notNull(neptunIdentifier);

    return Optional.ofNullable(this.conversionService.convert(
        this.instructorRepository.findByNeptunIdentifier(neptunIdentifier), Instructor.class));
  }
}
