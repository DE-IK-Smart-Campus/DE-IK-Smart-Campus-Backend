package hu.unideb.smartcampus.service.api.rss.facebook;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacebookEvent {

  @JsonSerialize
  @JsonProperty("description")
  private String description;
  
  @JsonSerialize
  @JsonProperty("name")
  private String name;

  @JsonSerialize
  @JsonProperty("start_time")
  private String startTime;
  
  @JsonSerialize
  @JsonProperty("end_time")
  private String endTime;
  
  @JsonSerialize
  @JsonProperty("id")
  private String id;

  @JsonSerialize
  @JsonProperty("place")
  private FacebookEventPlace place;
}
