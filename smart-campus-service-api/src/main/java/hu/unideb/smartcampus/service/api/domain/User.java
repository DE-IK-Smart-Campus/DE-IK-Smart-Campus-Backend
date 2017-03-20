package hu.unideb.smartcampus.service.api.domain;

import static hu.unideb.smartcampus.shared.exclusion.FieldExclusion.EXCLUDE_PASSWORD;

import java.util.List;
import java.util.Set;
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
   * The role of the user.
   */
  private final Role role;

  private final List<SubjectDetails> subjectDetailsSet;

  /**
   * Builder pattern for creating user.
   * @param id the id of the user
   * @param username the username of the user
   * @param password the password of the user
   * @param role the role of the user
   */
  @Builder
  public User(final Long id, final String username, final String password, final Role role, final List<SubjectDetails> subjectDetailsSet) {
    super(id);
    this.username = username;
    this.password = password;
    this.role = role;
    this.subjectDetailsSet = subjectDetailsSet;
  }
}
