package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.persistence.entity.InstructorEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.service.api.InstructorService;
import hu.unideb.smartcampus.service.api.domain.Instructor;


@Service
public class InstructorServiceImpl implements InstructorService {

  private final InstructorRepository instructorRepository;
  private final ConversionService conversionService;

  @Autowired
  public InstructorServiceImpl(final InstructorRepository instructorRepository, final ConversionService conversionService) {
    this.instructorRepository = instructorRepository;
    this.conversionService = conversionService;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Instructor> getInstructorByName(final String name) {
    Assert.notNull(name);

    return Optional.ofNullable(this.conversionService.convert(this.instructorRepository.findByName(name), Instructor.class));
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

    this.instructorRepository.save(this.conversionService.convert(instructor, InstructorEntity.class));
  }
}
