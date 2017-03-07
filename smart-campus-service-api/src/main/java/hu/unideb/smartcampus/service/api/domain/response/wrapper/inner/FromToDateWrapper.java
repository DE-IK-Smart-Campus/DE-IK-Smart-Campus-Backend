package hu.unideb.smartcampus.service.api.domain.response.wrapper.inner;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Consulting hour from - to interval in Long.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FromToDateWrapper implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 7366277020071387665L;

  /**
   * From date in long.
   */
  private Long from;

  /**
   * To date in long.
   */
  private Long to;

  /**
   * Constructs a FromToDate instance.
   */
  @Builder
  public FromToDateWrapper(Long from, Long to) {
    this.from = from;
    this.to = to;
  }



}
