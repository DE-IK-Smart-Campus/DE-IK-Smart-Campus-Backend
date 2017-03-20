package hu.unideb.smartcampus.shared.iq;

import org.jivesoftware.smack.packet.ExtensionElement;

/**
 * Empty extension.
 *
 */
public class EmptyExtension implements ExtensionElement {

  @Override
  public String getElementName() {
    return "";
  }

  @Override
  public CharSequence toXML() {
    return "";
  }

  @Override
  public String getNamespace() {
    return "";
  }

}
