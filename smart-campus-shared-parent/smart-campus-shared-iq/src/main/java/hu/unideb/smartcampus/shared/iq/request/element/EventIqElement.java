package hu.unideb.smartcampus.shared.iq.request.element;

import java.util.Collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventIqElement extends BaseIqElement{

  public static final String TAG_NAME_DESCRIPTION = "desc";

  public static final String TAG_NAME_EVENT = "event";

  public static final String TAG_NAME_NAME = "name";

  public static final String TAG_NAME_START_TIME = "start";

  public static final String TAG_NAME_END_TIME = "end";

  public static final String TAG_NAME_EVENT_ID = "id";

  private String description;

  private String name;

  private Long startTime;

  private Long endTime;

  private String eventId;
  
  private LocationIqElement location;
  
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(openTag(TAG_NAME_EVENT));
    sb.append(tagIfNotNull(TAG_NAME_DESCRIPTION, description));
    sb.append(tagIfNotNull(TAG_NAME_NAME, name));
    sb.append(tagIfNotNull(TAG_NAME_START_TIME, startTime));
    sb.append(tagIfNotNull(TAG_NAME_END_TIME, endTime));
    sb.append(tagIfNotNull(TAG_NAME_EVENT_ID, eventId));
    sb.append(emptyIfNull(location).toString());
    sb.append(closeTag(TAG_NAME_EVENT));
    return sb.toString();
  }
  
}
