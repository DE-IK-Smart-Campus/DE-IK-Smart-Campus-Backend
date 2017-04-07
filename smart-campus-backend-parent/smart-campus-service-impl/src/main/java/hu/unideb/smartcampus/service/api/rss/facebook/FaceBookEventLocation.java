package hu.unideb.smartcampus.service.api.rss.facebook;

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
public class FaceBookEventLocation {

  @JsonSerialize
  @JsonProperty("city")
  private String city;
  
  @JsonSerialize
  @JsonProperty("country")
  private String country;
  
  @JsonSerialize
  @JsonProperty("latitude")
  private Double latitude;
  
  @JsonSerialize
  @JsonProperty("longitude")
  private Double longitude;
  
  @JsonSerialize
  @JsonProperty("street")
  private String street;
  
  @JsonSerialize
  @JsonProperty("zip")
  private String zipCode;
  
}
