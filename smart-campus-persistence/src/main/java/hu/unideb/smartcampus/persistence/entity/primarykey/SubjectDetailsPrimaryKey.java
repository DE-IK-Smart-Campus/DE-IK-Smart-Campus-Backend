package hu.unideb.smartcampus.persistence.entity.primarykey;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by gabichelsea on 3/18/17.
 */
@Data
public class SubjectDetailsPrimaryKey implements Serializable {

  private static final long serialVersionUID = 6478736460289841855L;

  private String subjectName;

  private String subjectType;
}
