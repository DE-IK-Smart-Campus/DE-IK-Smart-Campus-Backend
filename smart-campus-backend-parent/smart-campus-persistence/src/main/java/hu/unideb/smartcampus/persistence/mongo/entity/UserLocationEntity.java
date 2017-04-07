package hu.unideb.smartcampus.persistence.mongo.entity;

import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "userLocation")
public class UserLocationEntity extends BaseDocument {

  private Long userId;

  private Circle location;
  
  private Long timestamp;

  @Builder
  public UserLocationEntity(String id, Long userId, Circle location,
      Long timestamp) {
    super(id);
    this.userId = userId;
    this.location = location;
    this.timestamp = timestamp;
  }


}
