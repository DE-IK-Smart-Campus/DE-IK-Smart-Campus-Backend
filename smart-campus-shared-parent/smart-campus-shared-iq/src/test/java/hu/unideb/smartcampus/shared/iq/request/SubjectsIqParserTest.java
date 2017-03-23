package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.request.element.InstructorIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.SubjectIqElement;

/**
 * Subjects IQ parser test.
 */
public class SubjectsIqParserTest extends AbstractIqParserTest {

  private static final List<InstructorIqElement> IE_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "George"), new InstructorIqElement(2L, "Andre"));
  private static final List<InstructorIqElement> AI_INSTRUCTORS =
      Arrays.asList(new InstructorIqElement(1L, "Johny"), new InstructorIqElement(2L, "Emily"));
  private static final List<SubjectIqElement> SUBJECTS = Arrays.asList(
      new SubjectIqElement("AI", AI_INSTRUCTORS), new SubjectIqElement("IE", IE_INSTRUCTORS));
  private static final String STUDENT = "Emily Stock";
  private static final String FILE = "src/test/resources/askSubjects.xml";

  @Before
  public void before() {
    super.before();
  }

  @Test
  public void testParseXml() throws JAXBException {
    SubjectsIqRequest iq = SubjectsIqRequest.builder().student(STUDENT).subjects(SUBJECTS).build();
    SubjectsIqRequest result = (SubjectsIqRequest) runTest(iq);
    Assert.assertEquals(STUDENT, result.getStudent());
    Assert.assertEquals(SUBJECTS, result.getSubjects());
  }

  @Override
  protected String getFile() {
    return FILE;
  }

}
