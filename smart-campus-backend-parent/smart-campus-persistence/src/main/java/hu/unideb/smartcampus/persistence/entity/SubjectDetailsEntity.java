package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_SUBJECT_DETAILS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
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

  @ElementCollection
  @CollectionTable(name = "subject_details_teacher_names_relation", joinColumns = {
      @JoinColumn(name = "subject_details_name", referencedColumnName = "subject_name"),
      @JoinColumn(name = "subject_details_type", referencedColumnName = "subject_type"),
      @JoinColumn(name = "subject_details_start_period", referencedColumnName = "start_period"),
      @JoinColumn(name = "subject_details_end_period", referencedColumnName = "end_period")
  })
  @Column(name="teacher_name")
  private List<String> teacherNames;

  /**
   * Start date time.
   */
  @Id
  @Column(name = "start_period")
  private LocalDate startPeriod;

  /**
   * End date time.
   */
  @Id
  @Column(name = "end_period")
  private LocalDate endPeriod;

  /**
   * Builder.
   */
  @Builder
  public SubjectDetailsEntity(final String subjectName, final String subjectType, final List<String> teacherNames,
                              final LocalDate startPeriod, final LocalDate endPeriod) {
    this.subjectName = subjectName;
    this.subjectType = subjectType;
    this.teacherNames = teacherNames;
    this.startPeriod = startPeriod;
    this.endPeriod = endPeriod;
  }
}
