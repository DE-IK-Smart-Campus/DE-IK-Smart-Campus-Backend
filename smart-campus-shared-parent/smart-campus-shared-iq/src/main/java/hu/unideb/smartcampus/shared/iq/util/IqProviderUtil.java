package hu.unideb.smartcampus.shared.iq.util;

import lombok.experimental.UtilityClass;

/**
 * Provider util
 */
@UtilityClass
public class IqProviderUtil {

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

}
