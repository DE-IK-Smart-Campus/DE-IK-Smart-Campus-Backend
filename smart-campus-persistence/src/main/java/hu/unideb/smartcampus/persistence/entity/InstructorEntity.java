package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.ColumnName.InstructorColumnName.COLUMN_NAME_NAME;
import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_INSTRUCTOR;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Instructor entity.
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"consultingDates"})
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_INSTRUCTOR)
@NamedQueries({
    @NamedQuery(name = "InstructorEntity.getInstructorConsultingHoursByInstructorName",
        query = "SELECT instr.consultingDates FROM InstructorEntity instr WHERE instr.name = ?1"),
    @NamedQuery(name = "InstructorEntity.getInstructorsBySubjectName",
        query = "SELECT instr FROM InstructorEntity instr join instr.subjects s WHERE s.name = ?1"),
    @NamedQuery(name = "InstructorEntity.getInstructorsBySubjectId",
        query = "SELECT instr FROM InstructorEntity instr join instr.subjects s WHERE s.id = ?1"),
    @NamedQuery(name = "InstructorEntity.getInstructorConsultingDatesByIdAndGivenDate",
        query = "SELECT c FROM InstructorEntity instr join instr.consultingDates c WHERE instr.id = ?1 AND c.fromToDate.fromDate BETWEEN ?2 AND ?3")})
public class InstructorEntity extends BaseEntity<Long> {

  /**
   * Name of the instructor.
   */
  @NotNull
  @Size(min = 2, max = 128)
  @Column(name = COLUMN_NAME_NAME)
  private String name;

  /**
   * Instructor's consulting hours.
   */
  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(joinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "consulting_date_id", referencedColumnName = "id"))
  private Set<ConsultingDateEntity> consultingDates;

  /**
   * Subjects of the instructor.
   */
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(joinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
  private Set<SubjectEntity> subjects;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public InstructorEntity(final Long id, final String name,
      final Set<ConsultingDateEntity> consultingDates, final Set<SubjectEntity> subjects) {
    super(id);
    this.name = name;
    this.consultingDates = consultingDates;
    this.subjects = subjects;
  }



}
