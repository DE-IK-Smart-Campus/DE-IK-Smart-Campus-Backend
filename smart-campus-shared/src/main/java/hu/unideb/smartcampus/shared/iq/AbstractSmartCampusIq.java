package hu.unideb.smartcampus.shared.iq;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.namespace.QName;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.LazyStringBuilder;

/**
 * Base IQ for Smartcampus Application.
 */
@SuppressWarnings({"checkstyle:abbreviationaswordinname", "PMD"})
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractSmartCampusIq extends IQ {

  public static final String BASE_NAMESPACE = "http://inf.unideb.hu/smartcampus/";

  private static final String APOSTROPHE = "'";

  private static final String EMPTY = "";

  private static final String QUOTATION_MARKS = "\"";

  private static final String NS2_BEFORE = ":ns2";

  private static final String NS2_AFTER = "ns2:";

  /**
   * Base IQ constructor.
   */
  public AbstractSmartCampusIq(IQ iq) {
    super(iq);
  }

  /**
   * Base IQ constructor.
   */
  public AbstractSmartCampusIq(String element) {
    super(element, BASE_NAMESPACE);
    super.setStanzaId(UUID.randomUUID().toString());
  }

  @Override
  public IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    IQChildElementXmlStringBuilder moddedXml = createEmptyXmlBuilder();
    JAXBElement<? extends AbstractSmartCampusIq> element = getRealInstance();
    String resultXml = createXml(element);
    resetBuilder(moddedXml);
    moddedXml.append(resultXml);
    return moddedXml;
  }

  private IQChildElementXmlStringBuilder createEmptyXmlBuilder() {
    return new IQChildElementXmlStringBuilder(getExtension());
  }

  protected abstract ExtensionElement getExtension();

  private String createXml(JAXBElement<? extends AbstractSmartCampusIq> element) {
    StringWriter writer = new StringWriter();
    JAXBContext context;
    try {
      context = JAXBContext.newInstance(getIqClass());
      Marshaller jaxbMarshaller = context.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
      jaxbMarshaller.marshal(element, writer);
    } catch (JAXBException e) {
      // ERROR
    }
    return replaceXmlBlacklistedItems(writer);
  }

  private String replaceXmlBlacklistedItems(StringWriter writer) {
    String result = writer.toString();
    result = result.replaceAll(NS2_AFTER, EMPTY);
    result = result.replaceAll(NS2_BEFORE, EMPTY);
    result = result.replaceAll(QUOTATION_MARKS, APOSTROPHE);
    return result.replaceAll(getClosingTag(), EMPTY);
  }

  private String getClosingTag() {
    return "</" + getExtension().getElementName() + ">";
  }

  private JAXBElement<? extends AbstractSmartCampusIq> getRealInstance() {
    return new JAXBElement<>(new QName(BASE_NAMESPACE, getElement()), getIqClass(), getInstance());
  }

  protected abstract Object getInstance();

  protected abstract String getElement();

  private void resetBuilder(IQChildElementXmlStringBuilder xml) {
    Field f;
    try {
      f = xml.getClass().getSuperclass().getDeclaredField("sb");
      f.setAccessible(true);
      f.set(xml, new LazyStringBuilder());
    } catch (Exception e) {
      // ERROR.
    }
  }

  /**
   * Get stanza id.
   */
  public String getStanzaId() {
    return super.getStanzaId();
  }

  /**
   * Get packet id.
   */
  public String getPacketID() {
    return super.getPacketID();
  }

  /**
   * Get To.
   */
  public String getTo() {
    return super.getTo();
  }

  /**
   * Get From.
   */
  public String getFrom() {
    return super.getFrom();
  }

  /**
   * Get Error.
   */
  public XMPPError getError() {
    return super.getError();
  }

  /**
   * Get Type.
   */
  public Type getType() {
    return super.getType();
  }

  /**
   * Get Iq class.
   */
  protected abstract Class getIqClass();

}
