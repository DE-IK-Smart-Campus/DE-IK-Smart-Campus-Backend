package hu.unideb.smartcampus.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;


/**
 * Chat properties holder POJO.
 */
@Data
public class ChatProperties {

  /**
   * BOSH service URL.
   */
  @JsonProperty("bosh_service_url")
  private String boshServiceUrl;
  
  /**
   * Credentials URL.
   */
  @JsonProperty("credentials_url")
  private String credentialsUrl;
  
  /**
   * MUC domain.
   */
  
  @JsonProperty("muc_domain")
  private String mucDomain;

  /**
   * Constructs a ChatProperties instance.
   */
  @Builder
  public ChatProperties(String boshServiceUrl, String credentialsUrl, String mucDomain) {
    this.boshServiceUrl = boshServiceUrl;
    this.credentialsUrl = credentialsUrl;
    this.mucDomain = mucDomain;
  }
  
  

}
