package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_SUBJECT_DETAILS;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import hu.unideb.smartcampus.persistence.entity.primarykey.SubjectDetailsPrimaryKey;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Subject details entity.
 */
@Data
@NoArgsConstructor
@IdClass(SubjectDetailsPrimaryKey.class)
@Entity
@Table(name = TABLE_NAME_SUBJECT_DETAILS)
public class SubjectDetailsEntity implements Serializable {

  private static final long serialVersionUID = -6535943261548443134L;

  /**
   * Subject name.
   */
  @Id
  @Column(name = "subject_name")
  private String subjectName;

  /**
   * Subject type.
   */
  @Id
  @Column(name = "subject_type")
  private String subjectType;

  /**
   * Builder.
   */
  @Builder
  public SubjectDetailsEntity(final String subjectName, final String subjectType) {
    this.subjectName = subjectName;
    this.subjectType = subjectType;
  }
}
