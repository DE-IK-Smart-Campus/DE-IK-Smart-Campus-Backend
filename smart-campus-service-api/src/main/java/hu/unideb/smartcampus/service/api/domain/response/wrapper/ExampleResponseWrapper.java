package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import lombok.Builder;
import lombok.Data;

/**
 * Example wrapper.
 *
 */
@Data
public class ExampleResponseWrapper extends BaseWrapper {

  /**
   * Example field.
   */
  private final String example;

  /**
   * Constructs an ExampleResponseWrapper.
   */
  @Builder
  public ExampleResponseWrapper(final String example, final String messageType) {
    super(messageType);
    this.example = example;
  }
}
