package hu.unideb.smartcampus.shared.profile.name;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Profile name provider.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProfileName {

  /**
   * Integration profile for integration tests.
   */
  public static final String INTEGRATION_PROFILE = "integration";
}
