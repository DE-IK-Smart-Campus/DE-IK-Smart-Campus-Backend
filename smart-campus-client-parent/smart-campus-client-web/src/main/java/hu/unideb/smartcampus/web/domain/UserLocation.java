package hu.unideb.smartcampus.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLocation {

  private Double longitude;
  
  private Double latitude;

  private Double accuracy;
  
}
