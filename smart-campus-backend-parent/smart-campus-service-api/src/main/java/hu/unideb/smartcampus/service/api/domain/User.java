package hu.unideb.smartcampus.service.api.domain;

import static hu.unideb.smartcampus.shared.exclusion.FieldExclusion.EXCLUDE_PASSWORD;

import java.util.List;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.shared.enumeration.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * User domain object.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = EXCLUDE_PASSWORD)
public class User extends BaseObject<Long> {

  /**
   * The username of the user.
   */
  private final String username;

  /**
   * The password of the user.
   */
  private final String password;

  /**
   * The name of the user.
   */
  private final String fullName;

  /**
   * Neptun identifier of the user.
   */
  private final String neptunIdentifier;

  /**
   * The role of the user.
   */
  private final Role role;

  /**
   * Subject details list.
   */
  private final List<SubjectDetails> subjectDetailsList;

  /**
   * MUC chat list.
   */
  private final List<String> mucChatList;

  /**
   * Chat list.
   */
  private final List<String> singleChatList;

  /**
   * Custom event list.
   */
  private final List<CustomEvent> customEventList;

  /**
   * Course appointment list.
   */
  private final List<CourseAppointment> courseAppointmentList;

  /**
   * Builder pattern for creating user.
   * 
   * @param id the id of the user
   * @param username the username of the user
   * @param password the password of the user
   * @param role the role of the user
   */
  @Builder
  public User(final Long id, final String username, final String password, final Role role,
      final List<SubjectDetails> subjectDetailsList, final String fullName,
      final String neptunIdentifier,
      final List<String> mucChatList,
      final List<String> singleChatList,
      final List<CustomEvent> customEventList,
      final List<CourseAppointment> courseAppointmentList) {
    super(id);
    this.username = username;
    this.password = password;
    this.role = role;
    this.subjectDetailsList = subjectDetailsList;
    this.fullName = fullName;
    this.neptunIdentifier = neptunIdentifier;
    this.mucChatList = mucChatList;
    this.singleChatList = singleChatList;
    this.customEventList = customEventList;
    this.courseAppointmentList = courseAppointmentList;
  }
}
