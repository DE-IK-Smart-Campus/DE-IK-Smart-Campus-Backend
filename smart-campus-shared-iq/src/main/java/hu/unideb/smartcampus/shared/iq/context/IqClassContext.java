package hu.unideb.smartcampus.shared.iq.context;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import hu.unideb.smartcampus.shared.iq.request.AbstractSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.ObjectFactory;

/**
 * IQ class context holder.
 */
public final class IqClassContext {

  /**
   * Context path.
   */
  private static final String CONTEXT_PATH = "hu.unideb.smartcampus.shared.iq.request";

  /**
   * IQ classes.
   */
  private static final Class[] IQ_CLASSES;

  static {
    Set<Class<? extends AbstractSmartCampusIq>> allClasses = getAllClasses();
    IQ_CLASSES = convertToClasses(allClasses);
  }

  /**
   * Get IQ classes.
   * 
   * @return IQ classes.
   */
  public static Class[] getIqClasses() {
    return IQ_CLASSES.clone();
  }

  private static Class[] convertToClasses(Set<Class<? extends AbstractSmartCampusIq>> allClasses) {
    List<Class<? extends AbstractSmartCampusIq>> classList = convertToList(allClasses);
    Class[] classes = classList.toArray(new Class[classList.size() + 1]);
    classes[classList.size()] = ObjectFactory.class;
    return classes;
  }

  private static List<Class<? extends AbstractSmartCampusIq>> convertToList(
      Set<Class<? extends AbstractSmartCampusIq>> allClasses) {
    return allClasses.stream().collect(Collectors.toList());
  }

  private static Set<Class<? extends AbstractSmartCampusIq>> getAllClasses() {
    Reflections reflections = new Reflections(CONTEXT_PATH);
    return reflections.getSubTypesOf(AbstractSmartCampusIq.class);
  }

  private IqClassContext() {
    // for PMD
  }

}
