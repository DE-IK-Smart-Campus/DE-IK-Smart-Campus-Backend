package hu.unideb.smartcampus.service.api.util;

import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.service.api.domain.User;
import hu.unideb.smartcampus.shared.enumeration.Role;
import hu.unideb.smartcampus.webservice.api.neptun.NeptunInfo;

/**
 * Role util.
 */
@Component
public class RoleUtil {

  private static final String STAFF = "staff";

  private static final String FACULTY = "faculty";

  private static final String STUDENT = "student";

  /**
   * Get role by Neptun info.
   */
  public Role getRoleByNeptunInfo(NeptunInfo neptunInfo) {
    String eduPersonPrimaryAffiliation =
        neptunInfo.getMemberInfo().getEduPersonPrimaryAffiliation();
    Role role = Role.UNKNOWN;
    switch (eduPersonPrimaryAffiliation) {
      case STUDENT:
        role = Role.USER;
        break;
      case FACULTY:
        role = Role.FACULTY;
        break;
      case STAFF:
        role = Role.STAFF;
        break;
      default:
        break;
    }
    return role;
  }

  /**
   * Is user instructor?
   */
  public boolean isInstructor(User user) {
    return Role.STAFF.equals(user.getRole());
  }

  /**
   * Is user student?
   */
  public boolean isStudent(User user) {
    return !Role.STAFF.equals(user.getRole());
  }
}
