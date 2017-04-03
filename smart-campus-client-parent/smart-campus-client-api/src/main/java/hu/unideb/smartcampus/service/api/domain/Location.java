package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Location extends BaseObject<Long> {

  private final Double longitude;

  private final Double latitude;

  private final Double accuracy;

  private final String username;

  private final Long timestamp;

  @Builder
  public Location(Long id, Double longitude, Double latitude, Double accuracy, String username,
      Long timestamp) {
    super(id);
    this.longitude = longitude;
    this.latitude = latitude;
    this.accuracy = accuracy;
    this.username = username;
    this.timestamp = timestamp;
  }



}
