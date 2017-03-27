package hu.unideb.smartcampus.shared.iq.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.TestIq;

/**
 * Subjects provider.
 */
@SuppressWarnings({"PMD"})
public class TestIqProvider extends IQProvider<TestIq> {

  @Override
  public TestIq parse(XmlPullParser parser, int initialDepth) throws Exception {
    boolean done = false;

    TestIq toReturn = new TestIq();
    int eventType;
    String elementName;
    while (!done) {
      eventType = parser.next();
      elementName = parser.getName();
      if (eventType == XmlPullParser.START_TAG) {
        if (elementName.equals("student")) {
        }
      } else if (eventType == XmlPullParser.END_TAG) {
        if (elementName.equals("student")) {
          toReturn.setStudent("ANYAD!");
        } else if (elementName.equals(TestIq.ELEMENT)) {
          done = true;
        }
      }
    }
    System.out.println("EZ MEGY VISSZA ANYÁDAT:" + toReturn);
    return toReturn;
  }

}
