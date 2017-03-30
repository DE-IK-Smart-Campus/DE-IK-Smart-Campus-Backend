package hu.unideb.smartcampus.web.controller;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.web.domain.XmppUser;


@RestController
@RequestMapping("/xmppcredentials")
public class PreBindController {


  private static final String SMARTCAMPUS_WEB = "/Smartcampus-Web";
  @Autowired
  private EjabberdUser ejabberdUser;

  @GetMapping
  @ResponseBody
  public XmppUser getPreBind() {
    XmppUser user = new XmppUser();
    AbstractXMPPConnection connection = ejabberdUser.getConnection();
    if (connection != null) {
      user.setJid(connection.getUser().asEntityBareJidString() + SMARTCAMPUS_WEB);
      user.setPassword(connection.getConfiguration().getPassword());
    }
    return user;
  }

}
