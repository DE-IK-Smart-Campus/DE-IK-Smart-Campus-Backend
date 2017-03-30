package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;

import org.jivesoftware.smack.provider.IQProvider;
import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.provider.InstructorConsultingDateIqProvider;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;

/**
 * InstructorConsultingDates IQ parser test.
 */
public class InstructorConsultingDatesIqParserTest extends AbstractParserTest {

  private static final String INSTRUCTOR_ID = "1";

  private static final FromToDateIqElement FROM_TO_DATE_IQ_ELEMENT =
      FromToDateIqElement.builder().from(1L).to(10L).build();

  private static final FromToDateIqElement FROM_TO_DATE_IQ_ELEMENT_SECOND =
      FromToDateIqElement.builder().from(11L).to(25L).build();

  private static final long CONSULTING_HOUR_ID_2L = 2L;

  private static final ConsultingDateIqElement CONSULTING_DATE_IQ_ELEMENT =
      ConsultingDateIqElement.builder().consultingHourId(CONSULTING_HOUR_ID_2L)
          .fromToDates(FROM_TO_DATE_IQ_ELEMENT).reservedSum(1).build();

  private static final ConsultingDateIqElement CONSULTING_DATE_IQ_ELEMENT_SECOND =
      ConsultingDateIqElement.builder().consultingHourId(3L)
          .fromToDates(FROM_TO_DATE_IQ_ELEMENT_SECOND).reservedSum(3).build();



  @Test
  public void testProvider() throws Exception {
    InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest();
    iq.setInstructorId(INSTRUCTOR_ID);
    iq.setConsultingDates(
        Arrays.asList(CONSULTING_DATE_IQ_ELEMENT, CONSULTING_DATE_IQ_ELEMENT_SECOND));
    InstructorConsultingDatesIqRequest parse = getParsedObject(iq);
    Assert.assertEquals(INSTRUCTOR_ID, parse.getInstructorId());
    Assert.assertEquals(
        Arrays.asList(CONSULTING_DATE_IQ_ELEMENT, CONSULTING_DATE_IQ_ELEMENT_SECOND),
        parse.getConsultingDates());
  }

  @Override
  public String getElement() {
    return InstructorConsultingDatesIqRequest.ELEMENT;
  }

  @Override
  public IQProvider getProvider() {
    return new InstructorConsultingDateIqProvider();
  }

}
