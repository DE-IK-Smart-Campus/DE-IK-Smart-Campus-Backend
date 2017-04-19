package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.ColumnName.InstructorColumnName.COLUMN_NAME_NAME;
import static hu.unideb.smartcampus.shared.table.ColumnName.InstructorColumnName.COLUMN_NAME_NEPTUN_IDENTIFIER;
import static hu.unideb.smartcampus.shared.table.ColumnName.InstructorColumnName.COLUMN_NAME_ROOM;
import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_INSTRUCTOR;

import java.util.Set;

import javax.persistence.CascadeType;
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
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_INSTRUCTOR)
@NamedQueries({
    @NamedQuery(name = "InstructorEntity.getInstructorConsultingHoursByInstructorName",
        query = "SELECT instr.consultingDates FROM InstructorEntity instr WHERE instr.name = ?1"),
    @NamedQuery(name = "InstructorEntity.getInstructorsBySubjectName",
        query = "SELECT instr FROM InstructorEntity instr join instr.subjects s WHERE s.subjectName = ?1"),
    @NamedQuery(name = "InstructorEntity.getInstructorByConsultingDateId",
        query = "SELECT instr FROM InstructorEntity instr join instr.consultingDates s WHERE s.id = ?1"),
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
   * Neptun identifier of the user.
   */
  @NotNull
  @Size(min = 1, max = 8)
  @Column(name = COLUMN_NAME_NEPTUN_IDENTIFIER, unique = true)
  private String neptunIdentifier;

  /**
   * Instructor's consulting hours.
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(joinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "consulting_date_id", referencedColumnName = "id"))
  private Set<ConsultingDateEntity> consultingDates;

  /**
   * Subjects of the instructor.
   */
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "instructor_subject_details",
      joinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"),
      inverseJoinColumns = {
          @JoinColumn(name = "subject_type", referencedColumnName = "subject_type"),
          @JoinColumn(name = "subject_name", referencedColumnName = "subject_name"),
          @JoinColumn(name = "start_period", referencedColumnName = "start_period"),
          @JoinColumn(name = "end_period", referencedColumnName = "end_period")
      })
  private Set<SubjectDetailsEntity> subjects;

  /**
   * Instructor's room.
   */
  @Size(min = 2, max = 128)
  @Column(name = COLUMN_NAME_ROOM)
  private String room;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public InstructorEntity(final Long id, final String name,
      final Set<ConsultingDateEntity> consultingDates, final Set<SubjectDetailsEntity> subjects,
      final String room, final String neptunIdentifier) {
    super(id);
    this.name = name;
    this.consultingDates = consultingDates;
    this.subjects = subjects;
    this.room = room;
    this.neptunIdentifier = neptunIdentifier;
  }


}
