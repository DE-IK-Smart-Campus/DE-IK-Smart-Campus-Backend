package hu.unideb.smartcampus.shared.iq.provider;

import hu.unideb.smartcampus.shared.iq.request.ChangeAttendanceIqRequest;

/**
 * Change attendance IQ provider.
 */
@SuppressWarnings({"PMD"})
public class ChangeAttendanceIqProvider
    extends BaseSmartCampusIqIntrospectionProvider<ChangeAttendanceIqRequest> {

  /**
   * Constructor.
   */
  public ChangeAttendanceIqProvider() {
    super(ChangeAttendanceIqRequest.class);
  }

}
