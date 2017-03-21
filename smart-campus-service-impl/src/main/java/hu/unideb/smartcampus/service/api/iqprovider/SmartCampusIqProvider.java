package hu.unideb.smartcampus.service.api.iqprovider;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.jivesoftware.smack.provider.IQProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import hu.unideb.smartcampus.shared.iq.AbstractSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.IqClassContext;

/**
 * Base class for every IQ provider class in the Smart campus application.
 *
 */
@SuppressWarnings({"PMD"})
public class SmartCampusIqProvider<T extends AbstractSmartCampusIq> extends IQProvider<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(SmartCampusIqProvider.class);

  protected JAXBContext jaxbContext;
  protected PullUnmarshaller unmarshaller;

  /**
   * Constructs a SmartCampusIqProvider instance.
   */
  public SmartCampusIqProvider() {
    try {
      jaxbContext = JAXBContext.newInstance(IqClassContext.getIqClasses());
      unmarshaller = new PullUnmarshaller(jaxbContext);
    } catch (JAXBException e) {
      LOGGER.error("Error on creating JAXBContext.", e);
    }
  }


  @Override
  @SuppressWarnings("unchecked")
  public T parse(XmlPullParser parser, int initialDepth)
      throws XmlPullParserException, IOException {
    try {
      return (T) unmarshaller.unmarshalSubTree(parser);
    } catch (IllegalStateException | JAXBException e) {
      LOGGER.error("Error on parsing.", e);
      throw new IOException(e);
    }
  }
}
