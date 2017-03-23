package hu.unideb.smartcampus.shared.iq.request;

import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;

/**
 * InstructorConsultingDates IQ parser test.
 */
public class InstructorConsultingDatesIqParserTest extends AbstractIqParserTest {

  private static final String INSTRUCTOR_ID = "1";

  private static final FromToDateIqElement FROM_TO_DATE_IQ_ELEMENT =
      FromToDateIqElement.builder().from(1L).to(10L).build();

  private static final long CONSULTING_HOUR_ID_2L = 2L;

  private static final ConsultingDateIqElement CONSULTING_DATE_IQ_ELEMENT =
      ConsultingDateIqElement.builder().consultingHourId(CONSULTING_HOUR_ID_2L)
          .fromToDates(FROM_TO_DATE_IQ_ELEMENT).reservedSum(1).build();


  private static final String FILE = "src/test/resources/instructorConsultingDates.xml";

  @Before
  public void before() {
    super.before();
  }

  @Test
  public void testParseXml() throws JAXBException {
    InstructorConsultingDatesIqRequest iq = new InstructorConsultingDatesIqRequest();
    iq.setInstructorId(INSTRUCTOR_ID);
    iq.setConsultingDates(Arrays.asList(CONSULTING_DATE_IQ_ELEMENT));
    InstructorConsultingDatesIqRequest result = (InstructorConsultingDatesIqRequest) runTest(iq);
    Assert.assertEquals(INSTRUCTOR_ID, result.getInstructorId());
  }

  @Override
  protected String getFile() {
    return FILE;
  }

}
