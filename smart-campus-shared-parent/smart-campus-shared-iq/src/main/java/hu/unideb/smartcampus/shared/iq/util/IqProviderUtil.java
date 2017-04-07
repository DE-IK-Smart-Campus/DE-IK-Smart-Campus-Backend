package hu.unideb.smartcampus.shared.iq.util;

import lombok.experimental.UtilityClass;

/**
 * Provider util
 */
@UtilityClass
public class IqProviderUtil {

  private static final String NULL = "null";

  /**
   * Opens tag.
   */
  public static String openTag(String tagName) {
    return "<" + tagName + ">";
  }

  /**
   * Close tag.
   */
  public static String closeTag(String tagName) {
    return "</" + tagName + ">";
  }

  /**
   * Open and then close tag.
   */
  public static String tag(String tagName, Object value) {
    return openTag(tagName) + value + closeTag(tagName);
  }

  /**
   * Parse int.
   */
  public static Integer parseInt(String text) {
    if (NULL.equals(text)) {
      return 0;
    }
    return Integer.valueOf(text);
  }

  /**
   * Parse long.
   */
  public static Long parseLong(String text) {
    if (NULL.equals(text)) {
      return 0L;
    }
    return Long.valueOf(text);
  }

}
