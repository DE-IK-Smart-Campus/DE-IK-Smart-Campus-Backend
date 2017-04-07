package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.factory.ExtensionElementFactory;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

/**
 * Subjects IQ parser test.
 */
public class TestIqParser {

  private static final List<InstructorIqElement> IE_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "George"), new InstructorIqElement(2L, "Andre"));
  private static final List<InstructorIqElement> AI_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "Johny"), new InstructorIqElement(2L, "Emily"));
  private static final List<SubjectIqElement> SUBJECTS = Arrays.asList(
      new SubjectIqElement("AI", AI_INSTRUCTORS), new SubjectIqElement("IE", IE_INSTRUCTORS));
  private static final String STUDENT = "Emily Stock";

  @Test
//  @Ignore
  public void testIqProvider() throws Exception {
    TestIq iq = new TestIq("student");
    IQChildElementXmlStringBuilder xml = new IQChildElementXmlStringBuilder(ExtensionElementFactory.getExtensionByElementName(TestIq.ELEMENT));
    IQChildElementXmlStringBuilder iqChildElementBuilder = iq.getIQChildElementBuilder(xml);
    System.out.println(iqChildElementBuilder);
  }

}
