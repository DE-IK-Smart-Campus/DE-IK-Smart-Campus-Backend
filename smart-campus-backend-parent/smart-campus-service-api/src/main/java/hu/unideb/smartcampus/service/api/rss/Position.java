package hu.unideb.smartcampus.service.api.rss;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class Position {

  private String city;

  private String country;

  private Double latitude;

  private Double longitude;

  private String street;

  private String zipCode;

  @Builder
  public Position(String city, String country, Double latitude, Double longitude, String street,
      String zipCode) {
    super();
    this.city = city;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
    this.street = street;
    this.zipCode = zipCode;
  }

}
