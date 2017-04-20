package hu.unideb.smartcampus.service.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.domain.ConsultingDate;
import hu.unideb.smartcampus.domain.Instructor;
import hu.unideb.smartcampus.domain.InstructorConsultingDate;
import hu.unideb.smartcampus.domain.Student;
import hu.unideb.smartcampus.domain.Subject;
import hu.unideb.smartcampus.service.api.converter.ConsultingDateListConverter;
import hu.unideb.smartcampus.service.api.converter.SignUpForConsultingDateConverter;
import hu.unideb.smartcampus.service.api.converter.SubjectListConverter;
import hu.unideb.smartcampus.service.api.iq.InstructorConsultingDatesIqHandler;
import hu.unideb.smartcampus.service.api.iq.ListInstructorSignedStudentsIqHandler;
import hu.unideb.smartcampus.service.api.iq.SignUpForConsultingDateIqHandler;
import hu.unideb.smartcampus.service.api.iq.SubjectsIqHandler;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListInstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;
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
    final InstructorConsultingDatesIqHandler iqHandler =
        new InstructorConsultingDatesIqHandler(ejabberdUser.getConnection(), domain, instructorId);
    final InstructorConsultingDatesIqRequest iqRequest = iqHandler.getResult();
    final Converter<List<ConsultingDateIqElement>, List<ConsultingDate>> converter =
        new ConsultingDateListConverter();
    return new Instructor(instructorId, iqRequest.getInstructorName(),
        converter.convert(iqRequest.getConsultingDates()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void signUpForConsultingDate(Long consultingHourId, Long duration, String reason) {
    LOGGER.info("Signing up for consulting date");
    final SignUpForConsultingDateIqHandler iqHandler = new SignUpForConsultingDateIqHandler(
        ejabberdUser.getConnection(),
        domain,
        consultingHourId,
        duration,
        reason);
    final SignUpForConsultingDateIqRequest iqRequest = iqHandler.getResult();
    final Converter<SignUpForConsultingDateIqRequest, String> converter =
        new SignUpForConsultingDateConverter();
    final String result = converter.convert(iqRequest);
    LOGGER.info("Consulting date ID: {}", consultingHourId);
    LOGGER.info("Result: {}", result);
  }

  @Override
  public List<InstructorConsultingDate> listThisWeekSignedStudents() {
    VCardManager manager = VCardManager.getInstanceFor(ejabberdUser.getConnection());
    VCard vcard = null;
    try {
      vcard = manager.loadVCard();
    } catch (NoResponseException | XMPPErrorException | NotConnectedException
        | InterruptedException e) {
    }
    ListInstructorConsultingDatesIqRequest result = null;
    if (vcard != null) {
      ListInstructorSignedStudentsIqHandler handler = new ListInstructorSignedStudentsIqHandler(
          ejabberdUser.getConnection(), domain, vcard.getField("UID"));
      result = handler.getResult();
    }
    return toDomainList(result);
  }

  private List<InstructorConsultingDate> toDomainList(
      ListInstructorConsultingDatesIqRequest request) {
    List<InstructorConsultingDate> result = new ArrayList<>();
    List<InstructorConsultingDateIqElement> dates = request.getDates();
    for (InstructorConsultingDateIqElement instructorConsultingDateIqElement : dates) {
      result.add(InstructorConsultingDate.builder()
          .students(instructorConsultingDateIqElement.getStudents().stream().map(this::convert)
              .collect(Collectors.toList()))
          .day(instructorConsultingDateIqElement.getDay())
          .consultingDateId(instructorConsultingDateIqElement.getConsultingDateId())
          .build());
    }
    return result;
  }

  private Student convert(StudentIqElement studentIq) {
    return Student.builder()
        .studentName(studentIq.getStudentName())
        .duration(studentIq.getDuration())
        .reason(studentIq.getReason())
        .build();
  }

}
