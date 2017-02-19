package hu.unideb.smartcampus.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hu.unideb.smartcampus.persistence.repository.SampleRepository;
import hu.unideb.smartcampus.service.api.SampleService;

/**
 * Implementation for {@link SampleService}.
 */
@Service
public class SampleServiceImpl implements SampleService {

  /**
   * Sample repository dep.
   */
  private final SampleRepository sampleRepository;

  /**
   * Constructor for dependency injection.
   */
  @Autowired
  public SampleServiceImpl(final SampleRepository sampleRepository) {
    this.sampleRepository = sampleRepository;
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public void sampleDependencyValidationMethod() {
    Assert.notNull(this.sampleRepository);
  }
}
