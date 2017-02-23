package hu.unideb.smartcampus.service.ejabberd.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Group request can be used to list groups in given host.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@SuppressWarnings({"PMD.SingularField"})
public class GroupRequest extends BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host the host.
   */
  public GroupRequest(String host) {
    this.host = host;
  }

}
