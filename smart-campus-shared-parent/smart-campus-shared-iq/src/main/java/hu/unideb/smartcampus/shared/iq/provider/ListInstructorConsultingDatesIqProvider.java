package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.CONSULTING_DATE_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.DATE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.DAY;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.ONE_WEEK;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.DURATION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.NEPTUN_IDENTIFIER;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.REASON;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.STUDENT_NAME;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListInstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * List instructor consulting dates IQ provider.
 */
@SuppressWarnings({"PMD"})
public class ListInstructorConsultingDatesIqProvider
    extends BaseSmartCampusIqProvider<ListInstructorConsultingDatesIqRequest> {

  @Override
  public ListInstructorConsultingDatesIqRequest parse(XmlPullParser parser, int initialDepth)
      throws Exception {
    List<InstructorConsultingDateIqElement> dates = new ArrayList<>();
    Boolean oneWeek = false;
    String neptunIdentifier = "";
    StudentIqElement student = new StudentIqElement();
    List<StudentIqElement> students = new ArrayList<>();
    InstructorConsultingDateIqElement consultingElement = new InstructorConsultingDateIqElement();
    int eventType = parser.getEventType();
    String text = "";
    boolean done = false;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase(STUDENT)) {
            student = new StudentIqElement();
          } else if (tagname.equalsIgnoreCase(STUDENTS)) {
            students = new ArrayList<>();
          } else if (tagname.equalsIgnoreCase(DATE)) {
            consultingElement = new InstructorConsultingDateIqElement();
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(STUDENT)) {
            students.add(student);
          } else if (tagname.equalsIgnoreCase(STUDENTS)) {
            consultingElement.setStudents(students);
          } else if (tagname.equalsIgnoreCase(CONSULTING_DATE_ID)) {
            consultingElement.setConsultingDateId(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(DATE)) {
            dates.add(consultingElement);
          } else if (tagname.equalsIgnoreCase(INSTRUCTORID)) {
            neptunIdentifier = text;
          } else if (tagname.equalsIgnoreCase(STUDENT_NAME)) {
            student.setStudentName(text);
          } else if (tagname.equalsIgnoreCase(NEPTUN_IDENTIFIER)) {
            student.setNeptunIdentifier(text);
          } else if (tagname.equalsIgnoreCase(REASON)) {
            student.setReason(text);
          } else if (tagname.equalsIgnoreCase(ONE_WEEK)) {
            oneWeek = Boolean.valueOf(text);
          } else if (tagname.equalsIgnoreCase(DAY)) {
            consultingElement.setDay(text);
          } else if (tagname.equalsIgnoreCase(DURATION)) {
            student.setDuration(text);
          } else if (tagname.equals(ListInstructorConsultingDatesIqRequest.ELEMENT)) {
            done = true;
          }
          break;
        default:
          break;
      }
    }
    return ListInstructorConsultingDatesIqRequest.builder()
        .instructorId(neptunIdentifier)
        .dates(dates)
        .oneWeek(oneWeek)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return ListInstructorConsultingDatesIqRequest.class;
  }

}
