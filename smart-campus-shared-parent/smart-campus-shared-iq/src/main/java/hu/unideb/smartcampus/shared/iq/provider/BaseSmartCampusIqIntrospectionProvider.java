package hu.unideb.smartcampus.shared.iq.provider;

import org.jivesoftware.smack.provider.IntrospectionProvider.IQIntrospectionProvider;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Base introspection provider.
 */
public class BaseSmartCampusIqIntrospectionProvider<T extends BaseSmartCampusIqRequest> extends IQIntrospectionProvider<T> {

  /**
   * Constructor.
   * @param elementClass class of the element.
   */
  protected BaseSmartCampusIqIntrospectionProvider(Class<T> elementClass) {
    super(elementClass);
  }
  
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return BaseSmartCampusIqRequest.class;
  } 
}
