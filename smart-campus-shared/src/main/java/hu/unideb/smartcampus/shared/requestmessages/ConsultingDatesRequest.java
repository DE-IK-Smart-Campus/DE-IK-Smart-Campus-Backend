package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * A message type.
 *
 */
@Data
@JsonDeserialize(builder = ConsultingDatesRequest.ConsultingDatesRequestBuilder.class)
public class ConsultingDatesRequest extends BaseRequest {

  /**
   * Name.
   */
  private final String name;

  /**
   * Constructs an instance.
   */
  @Builder
  public ConsultingDatesRequest(final String messageType, final String name) {
    super(messageType);
    this.name = name;
  }

  /**
   * Builder.
   *
   */
  @JsonPOJOBuilder(withPrefix = "")
  public static final class ConsultingDatesRequestBuilder {
  }


}
