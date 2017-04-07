package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;

import hu.unideb.smartcampus.shared.iq.factory.ExtensionElementFactory;

/**
 * Base IQ for Smartcampus Application.
 */
@SuppressWarnings({"checkstyle:abbreviationaswordinname", "PMD"})
public class BaseSmartCampusIqRequest extends IQ {

  public static final String BASE_NAMESPACE = "http://inf.unideb.hu/smartcampus/";

  /**
   * Base IQ constructor.
   */
  public BaseSmartCampusIqRequest(IQ iq) {
    super(iq);
  }

  /**
   * Base IQ constructor.
   */
  public BaseSmartCampusIqRequest(String element) {
    super(element, BASE_NAMESPACE);
  }

  @Override
  public IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    xml.rightAngleBracket();
    xml.append(toXml());
    return xml;
  }

  protected ExtensionElement getExtension() {
    return ExtensionElementFactory.getExtensionByElementName(getElement());
  }

  public String getElement() {
    return "";
  }

  protected String toXml() {
    throw new UnsupportedOperationException("Implement it in subclass.");
  }

  protected String openTag(String tagName) {
    return "<" + tagName + ">";
  }

  protected String closeTag(String tagName) {
    return "</" + tagName + ">";
  }
  
  protected String tag(String tagName, Object value) {
    return openTag(tagName) + value + closeTag(tagName);
  }

}
