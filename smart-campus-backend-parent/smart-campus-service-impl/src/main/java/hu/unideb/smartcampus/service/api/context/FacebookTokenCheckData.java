package hu.unideb.smartcampus.service.api.context;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookTokenCheckData {

  @JsonSerialize
  @JsonProperty("app_id")
  private String appId;

  @JsonSerialize
  @JsonProperty("is_valid")
  private Boolean valid;

  @JsonSerialize
  @JsonProperty("scopes")
  private List<String> scopes;

  @JsonSerialize
  @JsonProperty("application")
  private String application;
  
}
