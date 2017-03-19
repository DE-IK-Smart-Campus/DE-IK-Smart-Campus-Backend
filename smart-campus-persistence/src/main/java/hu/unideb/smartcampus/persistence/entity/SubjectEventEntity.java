package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_SUBJECT_EVENT_DETAILS;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_SUBJECT_EVENT_DETAILS)
public class SubjectEventEntity extends BaseEntity<Long> {

  @NotNull
  @Column(name = "room_location")
  private String roomLocation;

  @NotNull
  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumns({@JoinColumn(name = "subject_details_name", referencedColumnName = "subject_name"), @JoinColumn(name = "subject_details_type", referencedColumnName = "subject_type")})
  private SubjectDetailsEntity subjectDetailsEntity;

  @NotNull
  @ElementCollection
  @CollectionTable(name = "subject_event_appointment", joinColumns = @JoinColumn(name = "subject_event_id", referencedColumnName = "id"))
  private List<AppointmentTimeEntity> appointmentTimes;

  @Builder
  public SubjectEventEntity(final Long id, final String roomLocation, final SubjectDetailsEntity subjectDetailsEntity, final List<AppointmentTimeEntity> appointmentTimes) {
    super(id);
    this.roomLocation = roomLocation;
    this.subjectDetailsEntity = subjectDetailsEntity;
    this.appointmentTimes = appointmentTimes;
  }
}
