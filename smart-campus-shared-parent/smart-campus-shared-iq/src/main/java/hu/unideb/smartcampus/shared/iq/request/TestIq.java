package hu.unideb.smartcampus.shared.iq.request;

import org.jivesoftware.smack.packet.IQ;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Subject Iq.
 */
@Getter
@Setter
public class TestIq extends IQ {

  /**
   * Element.
   */
  public static final String ELEMENT = "testSmartcampus";

  public static final String NAMESPACE = "http://smartcampus";
  
  /**
   * User.
   */
  private String student;

  /**
   * Def contrcutros.
   */
  public TestIq() {
    super(ELEMENT, NAMESPACE);
  }

  /**
   * Constructs.
   */
  @Builder
  public TestIq(String student) {
    super(ELEMENT, NAMESPACE);
    this.student = student;
  }

  @Override
  protected IQChildElementXmlStringBuilder getIQChildElementBuilder(
      IQChildElementXmlStringBuilder xml) {
    xml.rightAngleBracket();
    xml.optElement("student", student);
    return xml;
  }


}
