package hu.unideb.smartcampus.shared.iq.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;

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
    Set<Class<? extends BaseSmartCampusIq>> allClasses = getAllClasses();
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

  private static Class[] convertToClasses(Set<Class<? extends BaseSmartCampusIq>> allClasses) {
    List<Class<? extends BaseSmartCampusIq>> classList = convertToList(allClasses);
    Class[] classes = classList.toArray(new Class[classList.size() + 1]);
    return classes;
  }

  private static List<Class<? extends BaseSmartCampusIq>> convertToList(
      Set<Class<? extends BaseSmartCampusIq>> allClasses) {
    List<Class<? extends BaseSmartCampusIq>> result = new ArrayList<>();
    for (Class<? extends BaseSmartCampusIq> clazz : allClasses) {
      result.add(clazz);
    }
    return result;
  }

  private static Set<Class<? extends BaseSmartCampusIq>> getAllClasses() {
    Reflections reflections = new Reflections(CONTEXT_PATH);
    return reflections.getSubTypesOf(BaseSmartCampusIq.class);
  }

  private IqClassContext() {
    // for PMD
  }

}
