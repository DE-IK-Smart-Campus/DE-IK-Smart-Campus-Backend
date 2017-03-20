package hu.unideb.smartcampus.shared.iq;

import org.jivesoftware.smack.packet.ExtensionElement;

/**
 * Empty extension.
 *
 */
public class SubjectExtension implements ExtensionElement {

  @Override
  public String getElementName() {
    return SubjectsIq.ELEMENT;
  }

  @Override
  public CharSequence toXML() {
    return "";
  }

  @Override
  public String getNamespace() {
    return SubjectsIq.NAMESPACE;
  }

}
