package hu.unideb.smartcampus.persistence.listener;

import java.util.UUID;

import javax.persistence.PrePersist;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;

/**
 * User entity listener for generating GUID before save custom event entity.
 */
public class CustomEventEntityListener {

  /**
   * Before persist generate GUID for entity.
   */
  @PrePersist
  public void beforePersist(CustomEventEntity entity) {
    if (entity.getGuid() == null) {
      entity.setGuid(UUID.randomUUID().toString());
    }

  }

}
