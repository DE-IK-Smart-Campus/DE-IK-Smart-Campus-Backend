package hu.unideb.smartcampus.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.SubjectEventEntity;

/**
 * Test for {@link CustomEventRepository}.
 */
public class SubjectEventRepositoryIntegrationTest extends BaseRepositoryIntegrationTestHelper {

  /**
   * Custom event repository.
   */
  @Autowired
  private SubjectEventRepository subjectEventRepository;

  @Test
  public void findBySubjectDetailsEntityAndRoomLocation() throws Exception {
    // given
    SubjectDetailsEntity subjectDetailsEntity = SubjectDetailsEntity
        .builder()
        .subjectName("Mesterséges intelligencia alapjai")
        .subjectType("LABORATORY")
        .startPeriod(LocalDate.of(2000, 2, 1))
        .endPeriod(LocalDate.of(2000, 5, 31))
        .build();

    // when

    // then
    List<SubjectEventEntity> findBySubjectDetailsEntity =
        subjectEventRepository.findBySubjectDetailsEntityAndRoomLocation(subjectDetailsEntity,
            "IK-202");

//    Assert.assertFalse(findBySubjectDetailsEntity.isEmpty());
  }

}
