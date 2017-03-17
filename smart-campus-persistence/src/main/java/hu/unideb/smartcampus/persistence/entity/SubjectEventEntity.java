package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_SUBJECT_EVENT_DETAILS;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
  @Column(name = "subject_details")
  private SubjectDetailsEntity subjectDetailsEntity;

  @NotNull
  @ElementCollection
  private List<AppointmentTimeEntity> appointmentTimes;
}
