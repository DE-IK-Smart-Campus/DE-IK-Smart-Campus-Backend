package hu.unideb.smartcampus.service.api.impl;

import java.util.ArrayList;
import java.util.List;

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

  @Autowired
  private UserRepository userRepository;

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  public void addChatToUser(String user, String partnerJid) {
    UserEntity userEntity = userRepository.findByUsername(user);
    List<String> singleChatList = userEntity.getSingleChatList();
    singleChatList.add(partnerJid);
    userRepository.save(userEntity);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional
  @Override
  public void addMucToUser(String user, String mucJid) {
    UserEntity userEntity = userRepository.findByUsername(user);
    List<String> mucChatList = userEntity.getMucChatList();
    mucChatList.add(mucJid);
    userRepository.save(userEntity);
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<String> listUserChats(String user) {
    UserEntity userEntity = userRepository.findByUsername(user);
    List<String> result;
    if (userEntity == null) {
      result = new ArrayList<>();
    } else {
      result = userEntity.getSingleChatList();
    }
    return result;
  }

  /**
   * {@inheritDoc}.
   */
  @Transactional(readOnly = true)
  @Override
  public List<String> listUserMucRooms(String user) {
    UserEntity userEntity = userRepository.findByUsername(user);
    List<String> result;
    if (userEntity == null) {
      result = new ArrayList<>();
    } else {
      result = userEntity.getMucChatList();
    }
    return result;
  }

}
