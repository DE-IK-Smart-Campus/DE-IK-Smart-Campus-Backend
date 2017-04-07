package hu.unideb.smartcampus.service.api.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLocation extends BaseObject<String> {

  private User user;

  private Double longitude;

  private Double latitude;

  private Double accuracy;

  private Long timestamp;

  @Builder
  public UserLocation(String id, User user, Double longitude, Double latitude, Double accuracy,
      Long timestamp) {
    super(id);
    this.user = user;
    this.longitude = longitude;
    this.latitude = latitude;
    this.accuracy = accuracy;
    this.timestamp = timestamp;
  }

}
