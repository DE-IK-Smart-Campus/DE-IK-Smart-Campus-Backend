package hu.unideb.smartcampus.service.api;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A representation of an event summary.
 *
 */
@Data
@EqualsAndHashCode
public class CaledarEventSummary {

  /**
   * The name of the class.
   */
  private String className;


  /**
   * The teacher of the class.
   */
  private String teacher;

  /**
   * The class code of the class.
   */
  private String classCode;

  /**
   * The code of the lecture.
   */
  private String lectureCode;

  /**
   * All argument constructor.
   * 
   * @param className the name of the class
   * @param teacher the teacher of the class
   * @param classCode the class code of the class
   * @param lectureCode the lecture code of the class
   */
  @Builder
  public CaledarEventSummary(String className, String teacher, String classCode,
      String lectureCode) {
    this.className = className;
    this.teacher = teacher;
    this.classCode = classCode;
    this.lectureCode = lectureCode;
  }



}
