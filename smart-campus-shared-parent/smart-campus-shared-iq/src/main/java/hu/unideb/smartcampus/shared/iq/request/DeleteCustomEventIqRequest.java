package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.EVENT_ID;
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
  private Long eventId;

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
  public DeleteCustomEventIqRequest(String student, Long eventId) {
    super(ELEMENT);
    this.student = student;
    this.eventId = eventId;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    builder.append(tag(EVENT_ID, eventId));
  }


  @Override
  public String getElement() {
    return ELEMENT;
  }
}
