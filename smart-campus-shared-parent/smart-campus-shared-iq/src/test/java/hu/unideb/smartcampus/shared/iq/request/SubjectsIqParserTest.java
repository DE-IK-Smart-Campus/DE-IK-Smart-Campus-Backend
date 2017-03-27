package hu.unideb.smartcampus.shared.iq.request;

import java.io.CharArrayReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlpull.mxp1.MXParser;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.provider.SubjectRequestIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

/**
 * Subjects IQ parser test.
 */
public class SubjectsIqParserTest {

  private static final List<InstructorIqElement> IE_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "George"), new InstructorIqElement(2L, "Andre"));
  private static final List<InstructorIqElement> AI_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "Johny"), new InstructorIqElement(2L, "Emily"));
  private static final List<SubjectIqElement> SUBJECTS = Arrays.asList(
      new SubjectIqElement("AI", AI_INSTRUCTORS), new SubjectIqElement("IE", IE_INSTRUCTORS));
  private static final String STUDENT = "Emily Stock";

  @Test
  @Ignore
  public void testIqProvider() throws Exception {
    SubjectsIqRequest iq = SubjectsIqRequest.builder().student(STUDENT).subjects(SUBJECTS).build();
//    IQChildElementXmlStringBuilder iqChildElementBuilder = iq.getIQChildElementBuilder((IQ)ExtensionElementFactory.getExtensionByElementName(SubjectsIqRequest.ELEMENT));
//    System.out.println(iqChildElementBuilder);
    SubjectRequestIqProvider provider = new SubjectRequestIqProvider();
    XmlPullParser parser = new MXParser();
    Reader in = new CharArrayReader(iq.toXml().toCharArray());
    parser.setInput(in);
    SubjectsIqRequest parse = provider.parse(parser, 0);
    Assert.assertEquals(STUDENT, parse.getStudent());
//    Assert.assertEquals(SUBJECTS, parse.getSubjects());
  }

}
