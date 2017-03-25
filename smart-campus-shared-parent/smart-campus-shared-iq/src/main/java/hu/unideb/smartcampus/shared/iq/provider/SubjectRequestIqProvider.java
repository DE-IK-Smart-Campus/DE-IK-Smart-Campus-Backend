package hu.unideb.smartcampus.shared.iq.provider;

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
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String tagname = parser.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          if (tagname.equalsIgnoreCase("student")) {

          }
          if (tagname.equalsIgnoreCase("subject")) {
            subject = new SubjectIqElement();
            instructors = new ArrayList<>();
            element = "subject";
          }
          if (tagname.equalsIgnoreCase("instructor")) {
            instructor = new InstructorIqElement();
            element = "instructor";
          }
          break;
        case XmlPullParser.TEXT:
          text = parser.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagname.equalsIgnoreCase("subject")) {
            subjects.add(subject);
          } else if (tagname.equalsIgnoreCase("instructor")) {
            instructors.add(instructor);
            subject.setInstructors(instructors);
          } else if (tagname.equalsIgnoreCase("name") && element.equals("subject")) {
            subject.setSubjectName(text);
          } else if (tagname.equalsIgnoreCase("name") && element.equals("instructor")) {
            instructor.setName(text);
          } else if (tagname.equalsIgnoreCase("instructorId")) {
            instructor.setInstructorId(Long.valueOf(text));
          } else if (tagname.equalsIgnoreCase("student")) {
            student = text;
          }
          break;

        default:
          break;
      }
      eventType = parser.next();
    }
    SubjectsIqRequest iq = new SubjectsIqRequest(student, subjects);
    return iq;
  }

}
