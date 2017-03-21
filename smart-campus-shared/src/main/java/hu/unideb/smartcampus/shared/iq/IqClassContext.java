package hu.unideb.smartcampus.shared.iq;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

/**
 * IQ class context holder.
 */
public final class IqClassContext {

  private IqClassContext() {
    // for PMD
  }
  
  /**
   * Context path.
   */
  private static final String CONTEXT_PATH = "hu.unideb.smartcampus.shared.iq";

  /**
   * IQ classes.
   */
  private static final Class[] IQ_CLASSES;

  static {
    Set<Class<? extends AbstractSmartCampusIq>> allClasses = getAllClasses();
    IQ_CLASSES = convertToClasses(allClasses);
  }

  private static Set<Class<? extends AbstractSmartCampusIq>> getAllClasses() {
    Reflections reflections = new Reflections(CONTEXT_PATH);
    Set<Class<? extends AbstractSmartCampusIq>> allClasses =
        reflections.getSubTypesOf(AbstractSmartCampusIq.class);
    return allClasses;
  }

  private static Class[] convertToClasses(Set<Class<? extends AbstractSmartCampusIq>> allClasses) {
    List<Class<? extends AbstractSmartCampusIq>> classList = convertToList(allClasses);
    return classList.toArray(new Class[classList.size()]);
  }

  private static List<Class<? extends AbstractSmartCampusIq>> convertToList(
      Set<Class<? extends AbstractSmartCampusIq>> allClasses) {
    return allClasses.stream().collect(Collectors.toList());
  }

  /**
   * Get IQ classes.
   * 
   * @return IQ classes.
   */
  public static Class[] getIqClasses() {
    return IQ_CLASSES.clone();
  }

}
