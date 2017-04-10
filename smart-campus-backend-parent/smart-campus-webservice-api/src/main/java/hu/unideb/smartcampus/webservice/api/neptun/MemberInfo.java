package hu.unideb.smartcampus.webservice.api.neptun;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Member info comes from Neptun.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberInfo {

  /**
   * UID of the member.
   */
  @JsonProperty("uid")
  private String uid;

  /**
   * Neptun identifier.
   */
  @JsonProperty("neptunIdentifier")
  private String neptunIdentifier;

  /**
   * Person status.
   */
  @JsonProperty("unidebPersonStatus")
  private String unidebPersonStatus;

  /**
   * Faculty.
   */
  @JsonProperty("unidebFaculty")
  private String unidebFaculty;

  /**
   * Unideb mail.
   */
  @JsonProperty("mail")
  private String mail;

  /**
   * Notification mail.
   */
  @JsonProperty("unidebNotificationEmail")
  private String unidebNotificationEmail;

  /**
   * JPEG photo in encoded Base64 string.
   */
  @JsonProperty("jpegPhoto")
  private String jpegPhoto;

  /**
   * Neptun kód.
   */
  @JsonProperty("neptun_kod")
  private String neptunKod;

  /**
   * Azonositó.
   */
  @JsonProperty("azonosito")
  private String azonosito;

  /**
   * Teljes név.
   */
  @JsonProperty("teljnev")
  private String teljnev;

  /**
   * Előtag.
   */
  @JsonProperty("elotag")
  private String elotag;

  /**
   * Vezetéknév.
   */
  @JsonProperty("vnev")
  private String vnev;

  /**
   * Utó név.
   */
  @JsonProperty("unev")
  private String unev;

  /**
   * Builder.
   */
  @Builder
  public MemberInfo(String uid, String neptunIdentifier, String unidebPersonStatus,
      String unidebFaculty, String mail, String unidebNotificationEmail, String jpegPhoto,
      String neptunKod, String azonosito, String teljnev, String elotag, String vnev, String unev) {
    this.uid = uid;
    this.neptunIdentifier = neptunIdentifier;
    this.unidebPersonStatus = unidebPersonStatus;
    this.unidebFaculty = unidebFaculty;
    this.mail = mail;
    this.unidebNotificationEmail = unidebNotificationEmail;
    this.jpegPhoto = jpegPhoto;
    this.neptunKod = neptunKod;
    this.azonosito = azonosito;
    this.teljnev = teljnev;
    this.elotag = elotag;
    this.vnev = vnev;
    this.unev = unev;
  }


}
