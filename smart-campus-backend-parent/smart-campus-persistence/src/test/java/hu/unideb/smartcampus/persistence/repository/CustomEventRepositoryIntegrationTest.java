package hu.unideb.smartcampus.persistence.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.CustomEventEntity;

/**
 * Test for {@link CustomEventRepository}.
 */
public class CustomEventRepositoryIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  /**
   * Custom event repository.
   */
  @Autowired
  private CustomEventRepository customEventRepository;

  @Test
  public void getEventsByUsername() throws Exception {
    // given

    // when

    // then
    List<CustomEventEntity> eventsByUsername = customEventRepository.getEventsByUsername("admin");
    Assert.assertFalse(eventsByUsername.isEmpty());
  }
  
  @Test
  public void getEventsByUserId() throws Exception {
    // given

    // when

    // then
    List<CustomEventEntity> eventsByUsername = customEventRepository.getEventsByUserId(1L);
    Assert.assertFalse(eventsByUsername.isEmpty());
  }

}
