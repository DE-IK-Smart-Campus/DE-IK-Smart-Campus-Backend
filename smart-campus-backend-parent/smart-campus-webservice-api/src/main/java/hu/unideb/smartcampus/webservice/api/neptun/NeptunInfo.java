package hu.unideb.smartcampus.webservice.api.neptun;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Neptun info request.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeptunInfo {

  @JsonProperty("result")
  private MemberInfo memberInfo;

  /**
   * Builder.
   */
  @Builder
  public NeptunInfo(MemberInfo memberInfo) {
    this.memberInfo = memberInfo;
  }



}
