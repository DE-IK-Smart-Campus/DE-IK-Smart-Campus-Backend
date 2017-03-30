package hu.unideb.smartcampus.web.domain;

import lombok.Data;

/**
 * XMPP User.
 */
@Data
public class XmppUser {
  /**
   * User JID with Resource.
   */
  private String jid;
  
  /**
   * User password. 
   */
  private String password;

}
