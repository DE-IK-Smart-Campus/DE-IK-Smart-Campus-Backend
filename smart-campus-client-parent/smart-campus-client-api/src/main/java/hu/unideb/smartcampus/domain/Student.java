package hu.unideb.smartcampus.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Student {
  /**
   * Student's name.
   */
  private String studentName;

  /**
   * Neptun identifier.
   */
  private String neptunIdentifier;

  /**
   * Reason of going.
   */
  private String reason;

  /**
   * Duration of the consulting.
   */
  private String duration;

  /**
   * Builder.
   */
  @Builder
  public Student(String studentName, String neptunIdentifier, String reason,
      String duration) {
    this.reason = reason;
    this.duration = duration;
    this.studentName = studentName;
    this.neptunIdentifier = neptunIdentifier;
  }
}
