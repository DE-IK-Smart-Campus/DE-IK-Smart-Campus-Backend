package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.STUDENT;

import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Add custom event IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AddCustomEventIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "addCustomEvent";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Custom IQ element.
   */
  private CustomEventIqElement customEvent;

  /**
   * Default constructor.
   */
  public AddCustomEventIqRequest() {
    super(ELEMENT);
  }

  /**
   * Constructs.
   */
  @Builder
  public AddCustomEventIqRequest(String student, CustomEventIqElement customEvent) {
    super(ELEMENT);
    this.student = student;
    this.customEvent = customEvent;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildEvent(builder);
  }

  private void buildEvent(StringBuilder builder) {
    builder.append(customEvent.toXml());
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}
