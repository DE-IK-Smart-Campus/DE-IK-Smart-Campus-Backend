package hu.unideb.smartcampus.service.api;

import hu.unideb.smartcampus.webservice.api.neptun.NeptunInfo;

/**
 * VCard service for updating vCard for users.
 */
public interface VCardService {

  /**
   * Update vCard.
   */
  void updatevCard(String user,String password);
  
  /**
   * Update vCard by Neptun info.
   */
  void updatevCard(String user,String password,NeptunInfo neptunInfo);
}
