package hu.unideb.smartcampus.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;
import hu.unideb.smartcampus.domain.ConsultingDate;
import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.domain.Subject;
import hu.unideb.smartcampus.service.api.converter.ConsultingDateListConverter;
import hu.unideb.smartcampus.service.api.converter.SubjectListConverter;
import hu.unideb.smartcampus.service.api.iq.InstructorConsultingDatesIqHandler;
import hu.unideb.smartcampus.service.api.iq.SubjectsIqHandler;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

/**
 * Service implementation.
 */
@Service
public class ConsultingHoursServiceImpl implements ConsultingHoursService {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ConsultingHoursServiceImpl.class);

  /**
   * Domain for smartcampus user.
   */
  @Resource(lookup = "java:global/smartcampus.xmpp.domain")
  private String domain;

  /**
   * Ejabberd user.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Subject> getSubjects() {
    LOGGER.info("Getting subjects for user");
    final SubjectsIqHandler iqHandler = new SubjectsIqHandler(ejabberdUser.getConnection(), domain);
    final SubjectsIqRequest iqRequest = iqHandler.getResult();
    final Converter<List<SubjectIqElement>, List<Subject>> converter = new SubjectListConverter();
    return converter.convert(iqRequest.getSubjects());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Instructor getInstructorByInstructorId(Long instructorId) {
    LOGGER.info("Getting instructor for instructor ID: {}", instructorId);
    final InstructorConsultingDatesIqHandler iqHandler = new InstructorConsultingDatesIqHandler(ejabberdUser.getConnection(), domain, instructorId);
    final InstructorConsultingDatesIqRequest iqRequest = iqHandler.getResult();
    final Converter<List<ConsultingDateIqElement>, List<ConsultingDate>> converter = new ConsultingDateListConverter();
    return new Instructor(instructorId, iqRequest.getInstructorName(), converter.convert(iqRequest.getConsultingDates()));
  }
}
