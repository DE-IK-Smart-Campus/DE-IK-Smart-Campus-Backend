package hu.unideb.smartcampus.service.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.CustomEventRepository;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.service.api.converter.toentity.CustomEventIqToCustomEventEntity;
import hu.unideb.smartcampus.service.api.converter.toiq.CustomEventEntityToCustomEventIq;
import hu.unideb.smartcampus.shared.iq.request.AddCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.DeleteCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListCustomEventIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event service impl.
 */
@Service
public class CustomEventServiceImpl implements CustomEventService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomEventServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CustomEventRepository customEventRepository;

  @Autowired
  private CustomEventEntityToCustomEventIq entityToIqConverter;

  @Autowired
  private CustomEventIqToCustomEventEntity iqToEntityConverter;

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<CustomEventIqElement> getCustomEventsByIq(ListCustomEventIqRequest iq) {
    LOGGER.info("Get custom events for {}", iq.getStudent());
    List<CustomEventEntity> customEvents =
        customEventRepository.getEventsByUsername(iq.getStudent());
    return customEvents.stream()
        .map(entityToIqConverter::convert)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  public void addCustomEntityByIq(AddCustomEventIqRequest iq) {
    LOGGER.info("Adding new custom event for {}", iq.getStudent());
    UserEntity user = userRepository.findByUsername(iq.getStudent());
    List<CustomEventEntity> customEvents = user.getCustomEvents();
    if (customEvents == null)
      customEvents = new ArrayList<>();
    CustomEventEntity entity = iqToEntityConverter.convert(iq.getCustomEvent());
    CustomEventEntity save = customEventRepository.save(entity);
    customEvents.add(save);
    userRepository.save(user);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  public void deleteCustomEntityByIq(DeleteCustomEventIqRequest iq) {
    LOGGER.info("Deleting custom event from {}", iq.getStudent());
    customEventRepository.deleteByGuid(iq.getEventGuid());
  }
}
