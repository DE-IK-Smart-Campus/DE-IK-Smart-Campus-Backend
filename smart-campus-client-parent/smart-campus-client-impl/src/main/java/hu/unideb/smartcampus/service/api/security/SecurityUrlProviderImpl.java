package hu.unideb.smartcampus.service.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.authentication.SecurityProperty;
import hu.unideb.smartcampus.service.api.authentication.SecurityUrlProvider;
import hu.unideb.smartcampus.shared.security.SmartCampusSecurityConstants;

@Service
public class SecurityUrlProviderImpl implements SecurityUrlProvider {


  private final SecurityPropertyProvider securityPropertyProvider;

  /**
   * Constructor.
   */
  @Autowired
  public SecurityUrlProviderImpl(SecurityPropertyProvider securityPropertyProvider) {
    this.securityPropertyProvider = securityPropertyProvider;
  }



  @Override
  public String produceUrl() {

    final String host = securityPropertyProvider.getProperty(SecurityProperty.HOST);
    final String port = securityPropertyProvider.getProperty(SecurityProperty.PORT);
    final String context = securityPropertyProvider.getProperty(SecurityProperty.CONTEXT);

    return "http://" + host + ":" + port + (context == null ? "" : context)
        + SmartCampusSecurityConstants.USER_DATA_PATH;
  }

}
