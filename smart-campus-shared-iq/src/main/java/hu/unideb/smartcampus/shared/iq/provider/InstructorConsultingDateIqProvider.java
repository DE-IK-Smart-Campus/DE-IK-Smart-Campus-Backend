package hu.unideb.smartcampus.shared.iq.provider;

import javax.xml.bind.JAXBException;

import hu.unideb.smartcampus.shared.iq.request.InstructorConsultingDatesIqRequest;

/**
 * Instructor consulting date IQ provider.
 */
@SuppressWarnings({"PMD"})
public class InstructorConsultingDateIqProvider
    extends SmartCampusIqProvider<InstructorConsultingDatesIqRequest> {

  /**
   * Constructor for provider.
   * 
   * @throws JAXBException on any error.
   */
  public InstructorConsultingDateIqProvider() throws JAXBException {
    super();
    // do not delete it, it should init the super class jaxb content.
  }

}
