package hu.unideb.smartcampus.shared.iq;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

/**
 * First test IQ.
 * 
 */
public class TestIq extends IQ {

  public static final String ELEMENT = "test";
  public static final String NAMESPACE = "http://inf.unideb.hu/smartcampus/test";

  /**
   * Def contrcutros.
   */
  public TestIq() {
    super(ELEMENT, NAMESPACE);
    things = new ArrayList<>();
  }

  /**
   * User.
   */
  private String user;

  /**
   * Things to good to have.
   */
  private List<Thing> things;

  /**
   * Constructs.
   */
  public TestIq(String user, List<Thing> things) {
    super(ELEMENT, NAMESPACE);
    this.user = user;
    this.things = things;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  protected IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    xml.rightAngleBracket();
    xml.halfOpenElement("user");
    xml.attribute("user", user);
    xml.closeEmptyElement();
    for (Thing item : things) {
      xml.append(item.toXml());
    }

    return xml;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public List<Thing> getThings() {
    return things;
  }

  public void setThings(List<Thing> things) {
    this.things = things;
  }

  /**
   * Thingy.
   */
  public static class Thing {

    /**
     * LOL.
     */
    public Thing(String thingName, String whatIsItGoodFor) {
      this.thingName = thingName;
      this.whatIsItGoodFor = whatIsItGoodFor;
    }

    /**
     * Thing name.
     */
    private String thingName;
    /**
     * Good for.
     */
    private String whatIsItGoodFor;

    /**
     * To XML.
     */
    public XmlStringBuilder toXml() {
      XmlStringBuilder xml = new XmlStringBuilder();
      xml.halfOpenElement("thing");
      xml.attribute("thingName", thingName);
      xml.optAttribute("goodFor", whatIsItGoodFor);
      xml.closeEmptyElement();
      return xml;
    }
  }

}
