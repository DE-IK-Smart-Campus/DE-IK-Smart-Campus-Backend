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

  private static final String GUID_TO_DELETE = "GUID-TO-DELETE";
  /**
   * Custom event repository.
   */
  @Autowired
  private CustomEventRepository customEventRepository;

  /**
   * Test get event by user name.
   */
  @Test
  public void getEventsByUsername() throws Exception {
    // given

    // when

    // then
    List<CustomEventEntity> eventsByUsername = customEventRepository.getEventsByUsername("admin");
    Assert.assertFalse(eventsByUsername.isEmpty());
  }

  /**
   * Test get event by user id.
   */
  @Test
  public void getEventsByUserId() throws Exception {
    // given

    // when

    // then
    List<CustomEventEntity> eventsByUsername = customEventRepository.getEventsByUserId(1L);
    Assert.assertFalse(eventsByUsername.isEmpty());
  }

  /**
   * Test delete by GUID
   */
  @Test
  public void deleteByGuid() throws Exception {
    // given

    // when

    // then
    Integer deleteByGuid = customEventRepository.deleteByGuid(GUID_TO_DELETE);
    
    Assert.assertNotNull(deleteByGuid);
    Assert.assertEquals(1,deleteByGuid.intValue());
  }

}
