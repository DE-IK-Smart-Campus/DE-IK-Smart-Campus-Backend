package hu.unideb.smartcampus.service.api;

import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;

/**
 * Facade for SRG and MUC service.
 */
public interface SrgMucFacade {

  /**
   * Generate MUC rooms.
   */
  List<String> generateMuc(List<SubjectEvent> events, String user);

  /**
   * Generate SRG groups Async.
   */
  void generateSrgAsync(List<SubjectEvent> events, String user);

}
