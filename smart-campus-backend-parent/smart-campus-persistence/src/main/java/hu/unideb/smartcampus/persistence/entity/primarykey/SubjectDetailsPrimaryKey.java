package hu.unideb.smartcampus.persistence.entity.primarykey;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Subject details primary key.
 */
@Data
public class SubjectDetailsPrimaryKey implements Serializable {

  private static final long serialVersionUID = 6478736460289841855L;

  /**
   * Subject name.
   */
  private String subjectName;

  /**
   * Subject type.
   */
  private String subjectType;

  /**
   * Start date time.
   */
  private LocalDate startPeriod;

  /**
   * End date time.
   */
  private LocalDate endPeriod;
}
