package hu.unideb.smartcampus.service.api.iqprovider;

import java.io.IOException;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import hu.unideb.smartcampus.shared.iq.TestIq;
import hu.unideb.smartcampus.shared.iq.TestIq.Thing;

/**
 * Test prodiver.
 *
 */
public class TestIqProvider extends IQProvider<TestIq> {

  @Override
  public TestIq parse(XmlPullParser parser, int initialDepth)
      throws XmlPullParserException, IOException {
    TestIq iq = new TestIq();
    boolean done = false;
    String goodFoor = "";
    String thingName = "";
    iq.setUser(parser.getAttributeValue("", "user"));
    while (!done) {
      int eventType = parser.next();
      
      if (eventType == XmlPullParser.START_TAG && "thing".equals(parser.getName())) {
        goodFoor = parser.getAttributeValue("", "goodFoor");
        thingName = parser.getAttributeValue("", "thingName");
      } else if (eventType == XmlPullParser.END_TAG && "thing".equals(parser.getName())) {
        // Create a new Item and add it to DiscoverItems.
        Thing thing = new Thing(thingName, goodFoor);
        iq.getThings().add(thing);
      } else if (eventType == XmlPullParser.END_TAG && "test".equals(parser.getName())) {
        done = true;
      }
    }
    return iq;
  }
}
