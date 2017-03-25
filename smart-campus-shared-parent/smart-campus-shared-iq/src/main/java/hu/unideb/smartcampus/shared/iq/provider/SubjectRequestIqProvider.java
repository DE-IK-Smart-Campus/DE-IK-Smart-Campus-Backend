package hu.unideb.smartcampus.shared.iq.provider;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.INSTRUCTOR;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.INSTRUCTORID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.NAME;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.STUDENT;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SubjectIqRequestFields.SUBJECT;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

/**
 * Subjects provider.
 */
@SuppressWarnings({"PMD"})
public class SubjectRequestIqProvider extends IQProvider<SubjectsIqRequest> {

  @Override
  public SubjectsIqRequest parse(XmlPullParser parser, int initialDepth) throws Exception {
    String student = null;
    List<SubjectIqElement> subjects = new ArrayList<>();
    List<InstructorIqElement> instructors = new ArrayList<>();
    SubjectIqElement subject = new SubjectIqElement();
    InstructorIqElement instructor = new InstructorIqElement();
    String element = "";
    int eventType = parser.getEventType();
    String text = "";
    boolean done = false;
    while (!done) {
      eventType = parser.next();
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase(STUDENT)) {

          }
          if (tagname.equalsIgnoreCase(SUBJECT)) {
            subject = new SubjectIqElement();
            instructors = new ArrayList<>();
            element = SUBJECT;
          }
          if (tagname.equalsIgnoreCase(INSTRUCTOR)) {
            instructor = new InstructorIqElement();
            element = INSTRUCTOR;
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase(SUBJECT)) {
            subjects.add(subject);
          } else if (tagname.equalsIgnoreCase(INSTRUCTOR)) {
            instructors.add(instructor);
            subject.setInstructors(instructors);
          } else if (tagname.equalsIgnoreCase(NAME) && element.equals(SUBJECT)) {
            subject.setSubjectName(text);
          } else if (tagname.equalsIgnoreCase(NAME) && element.equals(INSTRUCTOR)) {
            instructor.setName(text);
          } else if (tagname.equalsIgnoreCase(INSTRUCTORID)) {
            instructor.setInstructorId(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase(STUDENT)) {
            student = text;
          } else if (tagname.equals(SubjectsIqRequest.ELEMENT)) {
            done = true;
          }
          break;

        default:
          break;
      }
    }
    SubjectsIqRequest iq = new SubjectsIqRequest(student, subjects);
    return iq;
  }

}
