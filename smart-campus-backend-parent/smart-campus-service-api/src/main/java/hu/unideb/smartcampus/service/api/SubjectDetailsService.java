package hu.unideb.smartcampus.service.api;

import java.time.LocalDate;
import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.shared.primarykey.SubjectDetailsPrimaryKey;

/**
 * Subject details service.
 */
public interface SubjectDetailsService {

  /**
   * Get all subject details by user id.
   */
  List<SubjectDetails> getAllSubjectDetailsByUserId(Long userId);

  /**
   * Save.
   * @return 
   */
  SubjectDetails save(SubjectDetails subjectDetails);

  /**
   * Save.
   * @return 
   */
  List<SubjectDetails> save(List<SubjectDetails> subjectDetailsList);

  /**
   * Get all subject details by username.
   */
  List<SubjectDetails> getAllSubjectDetailsByUsername(String username);
  
  /**
   * Get subject details within range by username.
   */
  List<SubjectDetails> getSubjectDetailsWithinRangeByUsername(LocalDate from, LocalDate to, String username);
  
  SubjectDetails getByKey(SubjectDetailsPrimaryKey key);
}
