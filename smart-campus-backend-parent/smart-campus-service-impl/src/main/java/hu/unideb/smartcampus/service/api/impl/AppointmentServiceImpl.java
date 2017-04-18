package hu.unideb.smartcampus.service.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.CourseAppointmentEntity;
import hu.unideb.smartcampus.persistence.repository.CourseAppointmentRepository;
import hu.unideb.smartcampus.service.api.AppointmentService;

/**
 * Appointment service impl.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);

  @Autowired
  private CourseAppointmentRepository courseAppointmentRepository;

  @Transactional
  @Override
  public void setPresentForAppointment(Long appointmentId, boolean present) {
    LOGGER.info("Setting present attribute for appointment with id {} to {}", appointmentId,
        present);
    CourseAppointmentEntity appointment = courseAppointmentRepository.findOne(appointmentId);
    appointment.setWasPresent(present);
    courseAppointmentRepository.save(appointment);
  }

}
