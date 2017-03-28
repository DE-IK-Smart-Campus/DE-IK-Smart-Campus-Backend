package hu.unideb.smartcampus.shared.iq.factory;

import org.jivesoftware.smack.packet.ExtensionElement;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Extension element factory.
 * 
 * <p>
 * Use it instead of creating new sub-classes.
 * </p>
 */
public final class ExtensionElementFactory {

  private static final String EMPTY = "";

  private ExtensionElementFactory() {}

  /**
   * Creates an extension element instance with given elementname.
   * 
   * @param elementName requested elementname.
   * @return created extension element instance.
   */
  public static ExtensionElement getExtensionByElementName(final String elementName) {
    return new ExtensionElement() {

      @Override
      public CharSequence toXML() {
        return EMPTY;
      }

      @Override
      public String getElementName() {
        return elementName;
      }

      @Override
      public String getNamespace() {
        return BaseSmartCampusIqRequest.BASE_NAMESPACE;
      }
    };
  }

}
