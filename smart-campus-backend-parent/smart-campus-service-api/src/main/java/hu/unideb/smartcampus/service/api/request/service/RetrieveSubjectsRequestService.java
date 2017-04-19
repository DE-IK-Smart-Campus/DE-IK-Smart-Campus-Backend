package hu.unideb.smartcampus.service.api.request.service;

import java.util.List;

import hu.unideb.smartcampus.shared.wrapper.inner.SubjectWrapper;

/**
 * Service for retrieve student's subjects.
 */
public interface RetrieveSubjectsRequestService {

  /**
   * Get user subjects in the actual semester.
   */
  List<SubjectWrapper> getSubjects(String userId);
  
}
