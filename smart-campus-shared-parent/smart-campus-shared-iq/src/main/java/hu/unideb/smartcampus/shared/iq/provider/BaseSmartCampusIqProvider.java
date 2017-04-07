package hu.unideb.smartcampus.shared.iq.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIqRequest;

/**
 * Base provider.
 */
public class BaseSmartCampusIqProvider<T extends BaseSmartCampusIqRequest> extends IQProvider<T> {

  /**
   * {@inheritDoc}.
   */
  @Override
  public T parse(XmlPullParser parser, int initialDepth) throws Exception {
    return (T) new BaseSmartCampusIqRequest("base");
  }
  
  public Class<? extends BaseSmartCampusIqRequest> getHandledIq() {
    return BaseSmartCampusIqRequest.class;
  }


}
