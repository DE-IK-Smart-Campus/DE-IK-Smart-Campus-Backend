package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;

import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.VCardService;
import hu.unideb.smartcampus.service.api.util.RoleUtil;
import hu.unideb.smartcampus.service.api.xmpp.EjabberdUser;
import hu.unideb.smartcampus.shared.exception.XmppException;
import hu.unideb.smartcampus.webservice.api.neptun.MemberInfo;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunEndpointService;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunInfo;

/**
 * Impl.
 */
@Service
public class VCardServiceImpl implements VCardService {

  private static final String MIME_TYPE = "image/jpeg";

  private static final Logger LOGGER = LoggerFactory.getLogger(VCardServiceImpl.class);

  /**
   * Ejabberd user.
   */
  @Autowired
  private EjabberdUser ejabberdUser;

  /**
   * Neptun endpoint service.
   */
  @Autowired
  private NeptunEndpointService neptunEndpointService;

  /**
   * Role util.
   */
  @Autowired
  private RoleUtil roleUtil;

  @Override
  public void updatevCard(String user, String password) {
    login(user, password);
    NeptunInfo neptunInfoByUid;
    try {
      neptunInfoByUid = neptunEndpointService.getNeptunInfoByUid(user);
    } catch (IOException e) {
      LOGGER.error("Error on getting neptun information..", e);
      return;
    }
    VCardManager manager = VCardManager.getInstanceFor(ejabberdUser.getConnection());
    VCard vcard;
    try {
      vcard = manager.loadVCard();
    } catch (NoResponseException | XMPPErrorException | NotConnectedException
        | InterruptedException e) {
      LOGGER.error("Error on loading user vCard.", e);
      return;
    }

    setVcardFields(neptunInfoByUid, vcard);
    saveVCard(manager, vcard);
    ejabberdUser.logout();
  }

  private void saveVCard(VCardManager manager, VCard vcard) {
    try {
      manager.saveVCard(vcard);
    } catch (NoResponseException | XMPPErrorException | NotConnectedException
        | InterruptedException e) {
      LOGGER.error("Error on updating vCard.", e);
    }
  }

  private void login(String user, String password) {
    try {
      ejabberdUser.login(user, password);
    } catch (XmppException e1) {
      LOGGER.error("Error on logging into {} account to update vCard.", user);
    }
  }

  private void setVcardFields(NeptunInfo neptunInfoByUid, VCard vcard) {
    MemberInfo memberInfo = neptunInfoByUid.getMemberInfo();
    vcard.setFirstName(memberInfo.getVnev());
    vcard.setLastName(memberInfo.getUnev());
    vcard.setEmailHome(memberInfo.getUnidebNotificationEmail());
    vcard.setEmailWork(memberInfo.getMail());
    vcard.setOrganization(memberInfo.getUnidebFaculty());
    vcard.setOrganizationUnit(memberInfo.getOu());
    vcard.setNickName(memberInfo.getUid());
    vcard.setAvatar(memberInfo.getJpegPhoto(), MIME_TYPE);
    vcard.setField("NICKNAME", memberInfo.getTeljnev());
    vcard.setField("ROLE", roleUtil.getRoleByNeptunInfo(neptunInfoByUid).toString());
  }

  @Override
  public void updatevCard(String user, String password, NeptunInfo neptunInfo) {
    login(user, password);
    VCardManager manager = VCardManager.getInstanceFor(ejabberdUser.getConnection());
    VCard vcard;
    try {
      vcard = manager.loadVCard();
    } catch (NoResponseException | XMPPErrorException | NotConnectedException
        | InterruptedException e) {
      LOGGER.error("Error on loading user vCard.", e);
      return;
    }
    setVcardFields(neptunInfo, vcard);
    saveVCard(manager, vcard);
    ejabberdUser.logout();
  }

}
