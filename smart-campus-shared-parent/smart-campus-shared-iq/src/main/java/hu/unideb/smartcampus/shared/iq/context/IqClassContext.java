package hu.unideb.smartcampus.shared.iq.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import hu.unideb.smartcampus.shared.iq.provider.BaseSmartCampusIqIntrospectionProvider;
import hu.unideb.smartcampus.shared.iq.provider.BaseSmartCampusIqProvider;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * IQ class context holder.
 */
public final class IqClassContext {

  /**
   * Context path.
   */
  private static final String CONTEXT_PATH_IQ = "hu.unideb.smartcampus.shared.iq.request";

  /**
   * Provider context path.
   */
  private static final String CONTEXT_PATH_PROVIDER = "hu.unideb.smartcampus.shared.iq.provider";

  /**
   * IQ classes.
   */
  private static final Class<? extends BaseSmartCampusIqRequest>[] IQ_CLASSES;

  /**
   * IQ provider classes.
   */
  private static final Class<? extends BaseSmartCampusIqProvider>[] IQ_PROVIDER_CLASSES;

  /**
   * IQ introspection provider classes.
   */
  private static final Class<? extends BaseSmartCampusIqIntrospectionProvider>[] IQ_INTROSPECTION_PROVIDER_CLASSES;

  static {
    Set<Class<? extends BaseSmartCampusIqRequest>> iqs = getIqs();
    Set<Class<? extends BaseSmartCampusIqProvider>> providers = getProviderClasses();
    Set<Class<? extends BaseSmartCampusIqIntrospectionProvider>> introspectionProviders =
        getIntrospectionProviderClasses();
    IQ_CLASSES = convertToClasses(iqs);
    IQ_PROVIDER_CLASSES = convertToClasses(providers);
    IQ_INTROSPECTION_PROVIDER_CLASSES = convertToClasses(introspectionProviders);
  }

  /**
   * Get provider classes.
   * 
   * @return IQ provider classes.
   */
  public static Class<? extends BaseSmartCampusIqProvider>[] getIqProviderClasses() {
    return IQ_PROVIDER_CLASSES.clone();
  }

  /**
   * Get introspection provider classes.
   * 
   * @return IQ provider classes.
   */
  public static Class<? extends BaseSmartCampusIqIntrospectionProvider>[] getIqIntrospectionProviderClasses() {
    return IQ_INTROSPECTION_PROVIDER_CLASSES.clone();
  }

  private static Set<Class<? extends BaseSmartCampusIqProvider>> getProviderClasses() {
    return getByClass(CONTEXT_PATH_PROVIDER, BaseSmartCampusIqProvider.class);
  }

  private static Set<Class<? extends BaseSmartCampusIqIntrospectionProvider>> getIntrospectionProviderClasses() {
    return getByClass(CONTEXT_PATH_PROVIDER, BaseSmartCampusIqIntrospectionProvider.class);
  }

  /**
   * Get IQ classes.
   * 
   * @return IQ classes.
   */
  public static Class<? extends BaseSmartCampusIqRequest>[] getIqClasses() {
    return IQ_CLASSES.clone();
  }

  private static <T> Class<? extends T>[] convertToClasses(Set<Class<? extends T>> allClasses) {
    List<Class<? extends T>> classList = convertToList(allClasses);
    return classList.toArray(new Class[classList.size()]);
  }

  private static <T> List<Class<? extends T>> convertToList(Set<Class<? extends T>> allClasses) {
    List<Class<? extends T>> result = new ArrayList<>();
    for (Class<? extends T> clazz : allClasses) {
      result.add(clazz);
    }
    return result;
  }

  private static Set<Class<? extends BaseSmartCampusIqRequest>> getIqs() {
    return getByClass(CONTEXT_PATH_IQ, BaseSmartCampusIqRequest.class);
  }

  private static <T> Set<Class<? extends T>> getByClass(String contextPath, Class<T> clazz) {
    Reflections reflections = new Reflections(contextPath);
    return reflections.getSubTypesOf(clazz);
  }

  /**
   * IQ's with providers in map.
   * 
   * @return IQ's with providers.
   * @throws InstantiationException when provider could not instanciated.
   * @throws IllegalAccessException when something goes wrong.
   */
  public static Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqProvider>> getIqWithProvider()
      throws InstantiationException, IllegalAccessException {
    Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqProvider>> result =
        new HashMap<>();
    for (Class<? extends BaseSmartCampusIqProvider> clazz : IQ_PROVIDER_CLASSES) {
      BaseSmartCampusIqProvider instance = clazz.newInstance();
      result.put(instance.getHandledIq(), clazz);
    }
    return result;
  }

  /**
   * IQ's with providers in map.
   * 
   * @return IQ's with providers.
   * @throws InstantiationException when provider could not instanciated.
   * @throws IllegalAccessException when something goes wrong.
   */
  public static Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqIntrospectionProvider>> getIqWithIntrospectionProvider()
      throws InstantiationException, IllegalAccessException {
    Map<Class<? extends BaseSmartCampusIqRequest>, Class<? extends BaseSmartCampusIqIntrospectionProvider>> result =
        new HashMap<>();
    for (Class<? extends BaseSmartCampusIqIntrospectionProvider> clazz : IQ_INTROSPECTION_PROVIDER_CLASSES) {
      BaseSmartCampusIqIntrospectionProvider instance = clazz.newInstance();
      result.put(instance.getHandledIq(), clazz);
    }
    return result;
  }

  private IqClassContext() {
    // for PMD
  }
}
