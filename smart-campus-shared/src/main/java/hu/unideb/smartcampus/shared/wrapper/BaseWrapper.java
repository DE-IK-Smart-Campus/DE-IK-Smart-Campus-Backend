package hu.unideb.smartcampus.shared.wrapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base wrapper for all response wrapper.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseWrapper {

  /**
   * Response message type.
   */
  private String messageType;
}
