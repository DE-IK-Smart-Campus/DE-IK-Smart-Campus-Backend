package hu.unideb.smartcampus.shared.iq.request;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.smartcampus.shared.iq.context.IqClassContext;

/**
 * Abstract IQ parser test.
 */
public abstract class AbstractIqParserTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIqParserTest.class);

  protected static JAXBContext JAXB_CONTEXT;

  public void before() {
    try {
      JAXB_CONTEXT = JAXBContext.newInstance(IqClassContext.getIqClasses());
    } catch (JAXBException e) {
      LOGGER.error("Error in JAXB init.", e);
    }
  }

  protected BaseSmartCampusIq runTest(BaseSmartCampusIq iq) throws JAXBException {
    marshall(iq);
    return unmarshall();
  }

  private BaseSmartCampusIq unmarshall() throws JAXBException {
    Unmarshaller unmarshaller = JAXB_CONTEXT.createUnmarshaller();
    return (BaseSmartCampusIq) unmarshaller.unmarshal(new File(getFile()));
  }

  private void marshall(BaseSmartCampusIq iq) throws JAXBException, PropertyException {
    Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(iq, new File(getFile()));
  }

  protected abstract String getFile();

}
