package hu.unideb.smartcampus.service.api.request.service;

import java.util.List;

import hu.unideb.smartcampus.shared.wrapper.inner.ConsultingDateWrapper;

/**
 * Service for retrieve instructor consulting hours.
 */
public interface RetrieveInstructorsConsultingDatesRequestService {

  /**
   * Get instructor consulting dates by id.
   */
  List<ConsultingDateWrapper> getConsultingDatesByInstructorId(Long instructorId);
  
  /**
   * Get instructor name by id.
   */
  String getInstructorNameById(Long instructorId);
  
}
