package hu.unideb.smartcampus.service.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An enumeration to determine the type of a student timetable event.
 *
 */
public enum CalendarEventType {

  TIMETABLE(".*-\\sÓrarend"), EXAM(".*-\\sVizsga"), TASK(".*\\s-\\sFeladat\\s-\\s.*");

  /**
   * The validator regular expression to determine type.
   */
  private String validatorRegexp;

  /**
   * Constructor with validator regular expression.
   * 
   * @param validatorRegexp the regular expression the instance represents.
   */
  private CalendarEventType(String validatorRegexp) {
    this.validatorRegexp = validatorRegexp;
  }


  /**
   * Determines the type of the given .ics event summary.
   * 
   * @param string the summary of the event
   * @return the descriptor of the event
   */
  public static CalendarEventType matches(String string) {
    for (CalendarEventType type : values()) {
      Pattern pattern = Pattern.compile(type.getValidatorRegexp());
      Matcher matcher = pattern.matcher(string);
      if (matcher.find()) {
        return type;
      }
    }
    return null;
  }

  private String getValidatorRegexp() {
    return validatorRegexp;
  }


}
