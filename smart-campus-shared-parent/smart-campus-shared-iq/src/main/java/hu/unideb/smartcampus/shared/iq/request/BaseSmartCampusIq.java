package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.factory.ExtensionElementFactory;

/**
 * Base IQ for Smartcampus Application.
 */
@SuppressWarnings({"checkstyle:abbreviationaswordinname", "PMD"})
public class BaseSmartCampusIq extends IQ {

  public static final String BASE_NAMESPACE = "http://inf.unideb.hu/smartcampus/";

  /**
   * Base IQ constructor.
   */
  public BaseSmartCampusIq(IQ iq) {
    super(iq);
  }

  /**
   * Base IQ constructor.
   */
  public BaseSmartCampusIq(String element) {
    super(element, BASE_NAMESPACE);
  }

  @Override
  public IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    xml.append(toXml());
    return xml;
  }

  protected ExtensionElement getExtension() {
    return ExtensionElementFactory.getExtensionByElementName(getElement());
  }

  protected String getElement() {
    return "";
  }

  protected String toXml() {
    throw new UnsupportedOperationException("Implement it in subclass.");
  }

}
