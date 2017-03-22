package hu.unideb.smartcampus.webservice.api.ejabberd.request.sharedroster;

import java.io.Serializable;
import hu.unideb.smartcampus.webservice.api.ejabberd.request.base.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Group request can be used to list groups in given host.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class GroupRequest extends BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host the host.
   */
  @Builder
  public GroupRequest(String host) {
    super(host);
  }

}
