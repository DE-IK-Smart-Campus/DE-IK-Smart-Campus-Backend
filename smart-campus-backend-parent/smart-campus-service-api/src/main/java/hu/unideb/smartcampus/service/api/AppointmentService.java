package hu.unideb.smartcampus.service.api;

/**
 * Appointment service.
 */
public interface AppointmentService {

  /**
   * Set present attribute for appointment id.
   * 
   * @param appointmentId appointment id.
   * @param present present.
   */
  void setPresentForAppointment(Long appointmentId, boolean present);

}
