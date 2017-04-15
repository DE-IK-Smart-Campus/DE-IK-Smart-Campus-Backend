package hu.unideb.smartcampus.service.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.persistence.repository.UserRepository;
import hu.unideb.smartcampus.service.api.UserChatService;

/**
 * User chat service implementation.
 */
@Service
public class UserChatServiceImpl implements UserChatService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserChatServiceImpl.class);

  private static final String AT = "@";

  @Autowired
  private UserRepository userRepository;

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  public void addChatToUser(String user, String partnerJid) {
    addJidToUser(user, partnerJid);
    addChangedUsers(user, partnerJid);
  }

  private void addChangedUsers(String user, String partnerJid) {
    try {
      String[] splited = partnerJid.split(AT);
      String username = splited[0];
      String domain = splited[1];
      String userWithDomain = user + AT + domain;
      addJidToUser(username, userWithDomain);
    } catch (Exception e) {
      LOGGER.error("Error on adding changed user.", e);
    }
  }

  private void addJidToUser(String user, String partnerJid) {
    LOGGER.info("Adding {} to user:{}", partnerJid, user);
    UserEntity userEntity = userRepository.findByUsername(user);
    Set<String> singleChatList = userEntity.getSingleChatList();
    singleChatList.add(partnerJid);
    userRepository.save(userEntity);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  public void addMucToUser(String user, String mucJid) {
    LOGGER.info("Adding MUC room {} to user:{}", mucJid, user);
    UserEntity userEntity = userRepository.findByUsername(user);
    Set<String> mucChatList = userEntity.getMucChatList();
    mucChatList.add(mucJid);
    userRepository.save(userEntity);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<String> listUserChats(String user) {
    LOGGER.info("Listing partners for {}", user);
    return new ArrayList<>(userRepository.getSingleChatListByUsername(user));
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<String> listUserMucRooms(String user) {
    LOGGER.info("Listing MUC rooms for {}", user);
    return new ArrayList<>(userRepository.getMucChatListByUsername(user));
  }

}
