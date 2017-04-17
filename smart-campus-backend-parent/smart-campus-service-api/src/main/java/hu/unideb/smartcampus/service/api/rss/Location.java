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
public class Location {

  private String name;

  private String id;

  private Position position;

  @Builder
  public Location(String name, String id, Position position) {
    super();
    this.name = name;
    this.id = id;
    this.position = position;
  }


}
