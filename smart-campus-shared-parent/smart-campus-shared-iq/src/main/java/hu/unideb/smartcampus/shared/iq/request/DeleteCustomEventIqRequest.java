package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.GUID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.STUDENT;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Delete custom event IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class DeleteCustomEventIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "deleteCustomEvent";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's custom events.
   */
  private String eventGuid;

  /**
   * Default constructor.
   */
  public DeleteCustomEventIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public DeleteCustomEventIqRequest(String student, String eventGuid) {
    super(ELEMENT);
    this.student = student;
    this.eventGuid = eventGuid;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(GUID, eventGuid));
  }


  @Override
  public String getElement() {
    return ELEMENT;
  }
}
