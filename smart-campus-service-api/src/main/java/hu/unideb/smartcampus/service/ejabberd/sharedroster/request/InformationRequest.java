package hu.unideb.smartcampus.service.ejabberd.sharedroster.request;

import java.io.Serializable;

import hu.unideb.smartcampus.service.ejabberd.request.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Information request can be used to request information about given group on given host.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class InformationRequest extends BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Group name.
   */
  private final String group;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host the host.
   * @param group the group, which information is required.
   */
  @Builder
  public InformationRequest(String host, String group) {
    super(host);
    this.group = group;
  }

}
