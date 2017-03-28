package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.CalendarSubjectsIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.CalendarSubjectIqElement;

/**
 * Calendar subjects IQ parser test.
 */
public class CalendarSubjectsIqRequestTest extends AbstractParserTest {

  private static final Long WHEN = 123L;
  private static final Long TO = 10L;
  private static final Long FROM = 1L;
  private static final String WHERE = "Room";
  private static final String DESCRIPTION = "description";
  private static final String STUDENT = "ExampleStudent";
  private static final String SUBJECTNAME = "AI";
  private static final List<CalendarSubjectIqElement> SUBJECTEVENTS =
      Arrays.asList(CalendarSubjectIqElement.builder().subjectName(SUBJECTNAME).from(FROM).to(TO)
          .when(WHEN).description(DESCRIPTION).where(WHERE).build());

  @Test
  public void testIqProvider() throws Exception {
    CalendarSubjectsIqRequest iq =
        CalendarSubjectsIqRequest.builder().student(STUDENT).subjectEvents(SUBJECTEVENTS).build();
    CalendarSubjectsIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(STUDENT, parse.getStudent());
    Assert.assertEquals(SUBJECTEVENTS, parse.getSubjectEvents());
  }

  @Override
  public String getElement() {
    return CalendarSubjectsIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new CalendarSubjectsIqProvider();
  }

}
