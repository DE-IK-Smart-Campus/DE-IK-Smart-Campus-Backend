package hu.unideb.smartcampus.shared.wrapper.inner;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Consulting hour from - to interval in Long.
 *
 */
@Data
public class FromToDateWrapper implements Serializable {

  /**
   * UID.
   */
  private static final long serialVersionUID = 7366277020071387665L;

  /**
   * From date in long.
   */
  private final Long from;

  /**
   * To date in long.
   */
  private final Long to;

  /**
   * Constructs a FromToDate instance.
   */
  @Builder
  public FromToDateWrapper(Long from, Long to) {
    this.from = from;
    this.to = to;
  }



}
