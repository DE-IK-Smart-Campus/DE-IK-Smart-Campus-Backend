package hu.unideb.smartcampus.service.api.iqprovider;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.jivesoftware.smack.provider.IQProvider;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import hu.unideb.smartcampus.shared.iq.AbstractSmartCampusIq;

/**
 * Base class for every IQ provider class in the Smart campus application.
 *
 */
@SuppressWarnings({"PMD"})
public class SmartCampusIqProvider<T extends AbstractSmartCampusIq> extends IQProvider<T> {

  private static final String CONTEXT_PATH = "hu.unideb.smartcampus.shared.iq";

  private static final Logger LOGGER = LoggerFactory.getLogger(SmartCampusIqProvider.class);

  protected JAXBContext jaxbContext;
  protected PullUnmarshaller unmarshaller;

  /**
   * Constructs a SmartCampusIqProvider instance.
   */
  public SmartCampusIqProvider() {
    try {
      Set<Class<? extends AbstractSmartCampusIq>> allClasses = getAllClasses();
      jaxbContext = JAXBContext.newInstance(convertToClasses(allClasses));
      unmarshaller = new PullUnmarshaller(jaxbContext);
    } catch (JAXBException e) {
      LOGGER.error("Error on creating JAXBContext.", e);
    }
  }


  private Class[] convertToClasses(Set<Class<? extends AbstractSmartCampusIq>> allClasses) {
    List<Class<? extends AbstractSmartCampusIq>> list =
        allClasses.stream().collect(Collectors.toList());
    Class[] classes = new Class[list.size()];
    for (int i = 0; i < list.size(); i++) {
      classes[i] = list.get(i);
    }
    return classes;
  }


  private Set<Class<? extends AbstractSmartCampusIq>> getAllClasses() {
    Reflections reflections = new Reflections(CONTEXT_PATH);
    Set<Class<? extends AbstractSmartCampusIq>> allClasses =
        reflections.getSubTypesOf(AbstractSmartCampusIq.class);
    return allClasses;
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
