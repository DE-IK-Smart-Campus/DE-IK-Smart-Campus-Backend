package hu.unideb.smartcampus.shared.iq.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;

/**
 * Base provider.
 */
public class BaseSmartCampusIqProvider<T extends BaseSmartCampusIq> extends IQProvider<T> {

  @Override
  public T parse(XmlPullParser parser, int initialDepth) throws Exception {
    return (T) new BaseSmartCampusIq("base");
  }
  
  public Class<? extends BaseSmartCampusIq> getHandledIq() {
    return BaseSmartCampusIq.class;
  }


}
