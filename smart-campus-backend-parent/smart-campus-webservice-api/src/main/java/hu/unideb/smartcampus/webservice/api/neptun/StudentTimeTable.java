package hu.unideb.smartcampus.webservice.api.neptun;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Neptun subject request for asking user subjects.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentTimeTable {

  /**
   * If true request was successful.
   */
  @JsonProperty("success")
  private Boolean success;

  /**
   * Response message.
   */
  @JsonProperty("message")
  private String message;

  /**
   * Subjects.
   */
  @JsonProperty("result")
  private List<StudentCourse> courses;

  /**
   * Builder.
   */
  @Builder
  public StudentTimeTable(Boolean success, String message, List<StudentCourse> courses) {
    this.success = success;
    this.message = message;
    this.courses = courses;
  }


}
