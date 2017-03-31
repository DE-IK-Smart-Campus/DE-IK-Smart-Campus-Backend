package hu.unideb.smartcampus.service.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;
import hu.unideb.smartcampus.persistence.repository.CustomEventRepository;
import hu.unideb.smartcampus.service.api.CustomEventService;
import hu.unideb.smartcampus.service.api.converter.toiq.CustomEventEntityToCustomEventIq;
import hu.unideb.smartcampus.shared.iq.request.CustomEventListIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;

/**
 * Custom event service impl.
 */
public class CustomEventServiceImpl implements CustomEventService {

  @Autowired
  private CustomEventRepository customEventRepository;

  @Autowired
  private CustomEventEntityToCustomEventIq converter;

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<CustomEventIqElement> getCustomEventsByIq(CustomEventListIqRequest iq) {
    List<CustomEventEntity> customEvents =
        customEventRepository.getEventsByUsername(iq.getStudent());
    return customEvents.stream()
        .map(converter::convert)
        .collect(Collectors.toList());
  }

}
