package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.ONE_WEEK;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.ListInstructorConsultingDatesIqFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.DURATION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.NEPTUN_IDENTIFIER;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.REASON;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.StudentIqElementFields.STUDENT_NAME;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;
import hu.unideb.smartcampus.shared.iq.request.ListInstructorConsultingDatesIqRequest;
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
    Boolean oneWeek = false;
    Long instructorId = 0L;
    StudentIqElement student = new StudentIqElement();
    List<StudentIqElement> students = new ArrayList<>();
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
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(STUDENT)) {
            students.add(student);
          } else if (tagname.equalsIgnoreCase(INSTRUCTORID)) {
            instructorId = Long.valueOf(text);
          } else if (tagname.equalsIgnoreCase(STUDENT_NAME)) {
            student.setStudentName(text);
          } else if (tagname.equalsIgnoreCase(NEPTUN_IDENTIFIER)) {
            student.setNeptunIdentifier(text);
          } else if (tagname.equalsIgnoreCase(REASON)) {
            student.setReason(text);
          } else if (tagname.equalsIgnoreCase(ONE_WEEK)) {
            oneWeek = Boolean.valueOf(text);
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
        .instructorId(instructorId)
        .students(students)
        .oneWeek(oneWeek)
        .build();
  }

  @Override
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return ListInstructorConsultingDatesIqRequest.class;
  }

}
