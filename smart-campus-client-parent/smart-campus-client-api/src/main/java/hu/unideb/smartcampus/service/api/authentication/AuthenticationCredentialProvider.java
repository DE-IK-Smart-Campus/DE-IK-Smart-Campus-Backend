package hu.unideb.smartcampus.service.api.authentication;

public interface AuthenticationCredentialProvider {

  SmartCampusAuthentication getAuthentication(String username, String password);
  
}
