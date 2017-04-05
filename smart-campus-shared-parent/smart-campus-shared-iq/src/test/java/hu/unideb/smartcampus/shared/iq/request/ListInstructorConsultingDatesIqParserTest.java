package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.ListInstructorConsultingDatesIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.StudentIqElement;

/**
 * MUC listing parser test.
 */
public class ListInstructorConsultingDatesIqParserTest extends AbstractParserTest {

  private static final Boolean ONE_WEEK = true;
  private static final Long INSTRUCTORID = 1L;
  private static final List<StudentIqElement> STUDENTS = Arrays.asList(
      StudentIqElement.builder()
          .studentName("Erik")
          .neptunIdentifier("ABC123")
          .reason("Thesis")
          .duration("30 minutes")
          .build(),
      StudentIqElement.builder()
          .studentName("John")
          .neptunIdentifier("789QWE")
          .reason("TDK")
          .duration("10 minutes")
          .build());

  /**
   * Test IQ provider.
   */
  @Test
  public void testIqProvider() throws Exception {
    ListInstructorConsultingDatesIqRequest iq =
        ListInstructorConsultingDatesIqRequest.builder()
            .instructorId(INSTRUCTORID)
            .students(STUDENTS)
            .oneWeek(ONE_WEEK)
            .build();
    ListInstructorConsultingDatesIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(INSTRUCTORID, parse.getInstructorId());
    Assert.assertEquals(STUDENTS, parse.getStudents());
    Assert.assertEquals(ONE_WEEK, parse.isOneWeek());
  }

  @Override
  public String getElement() {
    return ListInstructorConsultingDatesIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new ListInstructorConsultingDatesIqProvider();
  }

}
