package hu.unideb.smartcampus.persistence.entity.primarykey;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by gabichelsea on 3/18/17.
 */
@Data
public class SubjectDetailsPrimaryKey implements Serializable {

  private String subjectName;

  private String subjectType;
}
