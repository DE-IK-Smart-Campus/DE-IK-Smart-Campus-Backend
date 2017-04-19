package hu.unideb.smartcampus.shared.iq.request.element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionIqElement extends BaseIqElement {

  public static final String TAG_NAME_POSITION = "pos";
  
  public static final String TAG_NAME_CITY = "city";

  public static final String TAG_NAME_COUNTRY = "ctry";

  public static final String TAG_NAME_LATITUDE = "lat";

  public static final String TAG_NAME_LONGITUDE = "lon";
  
  public static final String TAG_NAME_STREET = "str";
  
  public static final String TAG_NAME_ZIPCODE = "zip";

  private String city;

  private String country;

  private Double latitude;

  private Double longitude;

  private String street;

  private String zipCode;

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(openTag(TAG_NAME_POSITION));
    sb.append(tagIfNotNull(TAG_NAME_CITY, city));
    sb.append(tagIfNotNull(TAG_NAME_COUNTRY, country));
    sb.append(tagIfNotNull(TAG_NAME_LATITUDE, latitude));
    sb.append(tagIfNotNull(TAG_NAME_LONGITUDE, longitude));
    sb.append(tagIfNotNull(TAG_NAME_STREET, street));
    sb.append(tagIfNotNull(TAG_NAME_ZIPCODE, zipCode));
    sb.append(closeTag(TAG_NAME_POSITION));
    return sb.toString();
  }

}
