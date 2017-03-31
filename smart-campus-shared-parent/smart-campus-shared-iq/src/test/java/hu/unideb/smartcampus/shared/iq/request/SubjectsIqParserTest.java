package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.SubjectRequestIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

/**
 * Subjects IQ parser test.
 */
public class SubjectsIqParserTest extends AbstractParserTest {

  private static final List<InstructorIqElement> IE_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "George"), new InstructorIqElement(2L, "Andre"));
  private static final List<InstructorIqElement> AI_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "Johny"), new InstructorIqElement(2L, "Emily"));
  private static final List<SubjectIqElement> SUBJECTS = Arrays.asList(
      new SubjectIqElement("AI", AI_INSTRUCTORS), new SubjectIqElement("IE", IE_INSTRUCTORS));
  private static final String STUDENT = "Emily Stock";

  @Test
  public void testIqProvider() throws Exception {
    SubjectsIqRequest iq = SubjectsIqRequest.builder().student(STUDENT).subjects(SUBJECTS).build();
    SubjectsIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(SUBJECTS, parse.getSubjects());
  }

  @Override
  public String getElement() {
    return SubjectsIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new SubjectRequestIqProvider();
  }

}
