package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * Example message.
 *
 */
@Data
@JsonDeserialize(builder = ExampleRequest.ExampleRequestBuilder.class)
public class ExampleRequest extends BaseRequest {


  /**
   * Example field.
   */
  private final String example;

  /**
   * Constructs an example request.
   */
  @Builder
  public ExampleRequest(final String messageType, final String example) {
    super(messageType);
    this.example = example;
  }
  
  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static final class ExampleRequestBuilder {
  }


}
