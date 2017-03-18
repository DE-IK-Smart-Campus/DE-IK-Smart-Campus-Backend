package hu.unideb.smartcampus.persistence.entity.primarykey;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gabichelsea on 3/17/17.
 */
@Data
@NoArgsConstructor
@Embeddable
public class SubjectDetailsPrimaryKey implements Serializable {

  @Column(name = "subject_name")
  private String subjectName;

  @Column(name = "subject_type")
  private String subjectType;
}
