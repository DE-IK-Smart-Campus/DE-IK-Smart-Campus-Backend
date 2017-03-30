package hu.unideb.smartcampus.shared.iq.request;

import java.io.CharArrayReader;
import java.io.Reader;

import org.jivesoftware.smack.provider.IQProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.mxp1.MXParser;
import org.xmlpull.v1.XmlPullParser;

public abstract class AbstractParserTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractParserTest.class);

  public <T> T getParsedObject(T object) throws Exception {
    XmlPullParser parser = new MXParser();
    BaseSmartCampusIqRequest iq = (BaseSmartCampusIqRequest) object;
    StringBuilder xml = buildXml(iq);
    Reader in = new CharArrayReader(xml.toString().toCharArray());
    parser.setInput(in);
    return (T) getProvider().parse(parser, 0);
  }

  private StringBuilder buildXml(BaseSmartCampusIqRequest iq) {
    StringBuilder xml = new StringBuilder();
    xml.append("<" + iq.getElement() + ">");
    xml.append(iq.toXml());
    xml.append("</" + iq.getElement() + ">");
    LOGGER.debug(xml.toString());
    return xml;
  }

  public abstract String getElement();

  public abstract IQProvider getProvider();

}
