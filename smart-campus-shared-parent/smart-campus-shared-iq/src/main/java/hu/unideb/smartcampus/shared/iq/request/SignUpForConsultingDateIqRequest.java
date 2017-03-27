package hu.unideb.smartcampus.shared.iq.request;

import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.CONSULTING_HOUR_ID;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.DURATION;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.REASON;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.RESPONSE_MESSAGE;
import static hu.unideb.smartcampus.shared.iq.constant.Fields.SignUpForConsultingDateFields.USER_ID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Instructor consulting hours wrapper.
 */
@Getter
@Setter
@EqualsAndHashCode
public class SignUpForConsultingDateIqRequest extends BaseSmartCampusIq {

  /**
   * Element.
   */
  public static final String ELEMENT = "signUpForConsultingDate";

  /**
   * User's JID.
   */
  private String userId;

  /**
   * Consulting hour id.
   */
  private Long consultingHourId;

  /**
   * Reason of consulting.
   */
  private String reason;

  /**
   * Duration of consulting.
   */
  private String duration;

  /**
   * Response message.
   */
  private String responseMessage;

  /**
   * Builder.
   */
  @Builder
  public SignUpForConsultingDateIqRequest(String userId, Long consultingHourId, String reason,
      String duration, String responseMessage) {
    super(ELEMENT);
    this.userId = userId;
    this.consultingHourId = consultingHourId;
    this.reason = reason;
    this.duration = duration;
    this.responseMessage = responseMessage;
  }


  /**
   * Constructs an InstructorConsultingDatesRequestIq instance.
   */
  @Builder
  public SignUpForConsultingDateIqRequest() {
    super(ELEMENT);
  }

  @Override
  public String getElement() {
    return ELEMENT;
  }

  @Override
  protected String toXml() {
    StringBuilder builder = new StringBuilder();
    buildIq(builder);
    return builder.toString();
  }


  private void buildIq(StringBuilder builder) {
    builder.append(">");
    builder.append(tag(USER_ID, userId));
    builder.append(tag(CONSULTING_HOUR_ID, consultingHourId));
    builder.append(tag(REASON, reason));
    builder.append(tag(DURATION, duration));
    builder.append(tag(RESPONSE_MESSAGE, responseMessage));
  }

}

