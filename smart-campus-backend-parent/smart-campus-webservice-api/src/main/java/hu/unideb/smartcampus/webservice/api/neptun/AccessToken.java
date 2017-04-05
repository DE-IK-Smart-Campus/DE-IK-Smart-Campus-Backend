package hu.unideb.smartcampus.webservice.api.neptun;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Access token for Neptun REST.
 */
@Data
public class AccessToken {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private Integer expiresIn;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("scope")
  private String scope;
}
