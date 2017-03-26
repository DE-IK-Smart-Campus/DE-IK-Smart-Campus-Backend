package hu.unideb.smartcampus.shared.iq.provider;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import hu.unideb.smartcampus.shared.iq.context.IqClassContext;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * IQ class and IQ provider context test.
 */
public class IqClassContextTest {


  /**
   * Test IQ and Provider returning.
   */
  @Test
  public void testIqWithProvider() throws InstantiationException, IllegalAccessException {
    Map<Class<? extends BaseSmartCampusIq>, Class<? extends BaseSmartCampusIqProvider>> iqWithProvider =
        IqClassContext.getIqWithProvider();
    Assert.assertEquals(SubjectRequestIqProvider.class,
        iqWithProvider.get(SubjectsIqRequest.class));
    Assert.assertEquals(InstructorConsultingDateIqProvider.class,
        iqWithProvider.get(InstructorConsultingDatesIqRequest.class));
    Assert.assertEquals(SignUpForConsultingHourIqProvider.class,
        iqWithProvider.get(SignUpForConsultingDateIqRequest.class));
  }

}
