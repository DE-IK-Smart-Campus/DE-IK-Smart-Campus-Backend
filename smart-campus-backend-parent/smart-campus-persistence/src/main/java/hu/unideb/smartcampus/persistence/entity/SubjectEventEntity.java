package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_SUBJECT_EVENT_DETAILS;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Subject event entity.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_SUBJECT_EVENT_DETAILS)
public class SubjectEventEntity extends BaseEntity<Long> {

  private static final long serialVersionUID = 7503249866427740799L;

  /**
   * Room location.
   */
//  @NotNull
  @Column(name = "room_location")
  private String roomLocation;
  
  /**
   * Course code.
   */
//  @NotNull
  @Column(name = "course_code")
  private String courseCode;

  /**
   * Subject details entity.
   */
//  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
  @JoinColumns({
      @JoinColumn(name = "subject_details_name", referencedColumnName = "subject_name"),
      @JoinColumn(name = "subject_details_type", referencedColumnName = "subject_type"),
      @JoinColumn(name = "subject_details_start_period", referencedColumnName = "start_period"),
      @JoinColumn(name = "subject_details_end_period", referencedColumnName = "end_period")})
  private SubjectDetailsEntity subjectDetailsEntity;

  /**
   * Appointment times.
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @JoinColumns({
      @JoinColumn(name = "subject_event_id", referencedColumnName = "id")})
  @Deprecated
  private List<AppointmentTimeEntity> appointmentTimes;

  /**
   * Builder.
   */
  @Builder
  public SubjectEventEntity(final Long id, final String roomLocation,
      final SubjectDetailsEntity subjectDetailsEntity,
      final List<AppointmentTimeEntity> appointmentTimes,
      final String courseCode) {
    super(id);
    this.roomLocation = roomLocation;
    this.subjectDetailsEntity = subjectDetailsEntity;
    this.appointmentTimes = appointmentTimes;
    this.courseCode = courseCode;
  }
}
