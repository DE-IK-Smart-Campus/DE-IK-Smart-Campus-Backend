package hu.unideb.smartcampus.shared.iq.provider;

import javax.xml.bind.JAXBException;

import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * Subjects provider.
 */
@SuppressWarnings({"PMD"})
public class SubjectRequestIqProvider extends SmartCampusIqProvider<SubjectsIqRequest> {

  /**
   * Constructor for provider.
   * 
   * @throws JAXBException on any error.
   */
  public SubjectRequestIqProvider() throws JAXBException {
    super();
    // do not delete it, it should init the super class jaxb content.
  }

}
