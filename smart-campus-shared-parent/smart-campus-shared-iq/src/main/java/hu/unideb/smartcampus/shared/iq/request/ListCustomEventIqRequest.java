package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.CUSTOM_EVENTS;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.CustomEventIqRequestFields.STUDENT;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.smartcampus.shared.iq.request.element.CustomEventIqElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Custom event IQ request.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ListCustomEventIqRequest extends BaseSmartCampusIqRequest {

  /**
   * Element.
   */
  public static final String ELEMENT = "listCustomEvent";

  /**
   * Student's username.
   */
  private String student;

  /**
   * Student's custom events.
   */
  private List<CustomEventIqElement> customEvents;

  /**
   * Default constructor.
   */
  public ListCustomEventIqRequest() {
    super(ELEMENT);
    customEvents = new ArrayList<>();
  }

  /**
   * Constructs.
   */
  @Builder
  public ListCustomEventIqRequest(String student, List<CustomEventIqElement> customEvents) {
    super(ELEMENT);
    this.student = student;
    this.customEvents = customEvents;
  }

  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }

  private void buildIq(StringBuilder builder) {
    builder.append(tag(STUDENT, student));
    buildEvents(builder);
  }

  private void buildEvents(StringBuilder builder) {
    if (customEvents != null && !customEvents.isEmpty()) {
      builder.append(openTag(CUSTOM_EVENTS));
      for (CustomEventIqElement customIqElement : customEvents) {
        buildEvent(builder, customIqElement);
      }
      builder.append(closeTag(CUSTOM_EVENTS));
    }
  }

  private void buildEvent(StringBuilder builder, CustomEventIqElement customIqElement) {
    builder.append(customIqElement.toXml());
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }
}
