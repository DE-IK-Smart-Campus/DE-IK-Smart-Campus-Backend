package hu.unideb.smartcampus.shared.iq.request.element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationIqElement extends BaseIqElement {


  public static final String TAG_NAME_NAME = "loc_name";

  public static final String TAG_NAME_ID = "loc_id";

  public static final String TAG_NAME_LOCATION = "loc";

  private String name;

  private String id;

  private PositionIqElement position;

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(openTag(TAG_NAME_LOCATION));
    sb.append(tagIfNotNull(TAG_NAME_NAME, name));
    sb.append(tagIfNotNull(TAG_NAME_ID, id));
    sb.append(emptyIfNull(position).toString());
    sb.append(closeTag(TAG_NAME_LOCATION));
    return sb.toString();
  }

}
