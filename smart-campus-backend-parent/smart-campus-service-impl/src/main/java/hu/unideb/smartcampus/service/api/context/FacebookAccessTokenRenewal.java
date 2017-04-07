package hu.unideb.smartcampus.service.api.context;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookAccessTokenRenewal {

  @JsonSerialize
  @JsonProperty("access_token")
  private String accessToken;
  
  @JsonSerialize
  @JsonProperty("token_type")
  private String tokenType;
  
}
