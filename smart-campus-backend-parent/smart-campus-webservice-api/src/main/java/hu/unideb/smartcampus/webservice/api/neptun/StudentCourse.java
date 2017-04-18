package hu.unideb.smartcampus.webservice.api.neptun;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Subject appointment for asking subjects from Neptun.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentCourse {

  /**
   * Student neptun identifier.
   */
  @JsonProperty("student_neptunIdentifier")
  private String studentNeptunIdentifier;

  /**
   * Student name.
   */
  @JsonProperty("student_displayName")
  private String studentDisplayName;

  /**
   * Semester (for example:2016/17/1).
   */
  @JsonProperty("semester")
  private String semester;

  /**
   * Subject code.
   */
  @JsonProperty("subject_code")
  private String subjectCode;

  /**
   * Subject name.
   */
  @JsonProperty("subject_name")
  private String subjectName;

  /**
   * Course code.
   */
  @JsonProperty("course_code")
  private String courseCode;

  /**
   * Start time.
   */
  @JsonProperty("start_time")
  private String startTime;

  /**
   * End time.
   */
  @JsonProperty("end_time")
  private String endTime;

  /**
   * Lecturer neptun identifier.
   */
  @JsonProperty("lecturer_neptunIdentifier")
  private String lecturerNeptunIdentifier;

  /**
   * Lecturer display name.
   */
  @JsonProperty("lecturer_displayName")
  private String lecturerDisplayName;

  /**
   * Class room code.
   */
  @JsonProperty("classroom_code")
  private String classroomCode;

  /**
   * Builder.
   */
  @Builder
  public StudentCourse(String studentNeptunIdentifier, String studentDisplayName,
      String semester, String subjectCode, String subjectName, String courseCode, String startTime,
      String endTime, String lecturerNeptunIdentifier, String lecturerDisplayName,
      String classroomCode) {
    this.studentNeptunIdentifier = studentNeptunIdentifier;
    this.studentDisplayName = studentDisplayName;
    this.semester = semester;
    this.subjectCode = subjectCode;
    this.subjectName = subjectName;
    this.courseCode = courseCode;
    this.startTime = startTime;
    this.endTime = endTime;
    this.lecturerNeptunIdentifier = lecturerNeptunIdentifier;
    this.lecturerDisplayName = lecturerDisplayName;
    this.classroomCode = classroomCode;
  }



}
