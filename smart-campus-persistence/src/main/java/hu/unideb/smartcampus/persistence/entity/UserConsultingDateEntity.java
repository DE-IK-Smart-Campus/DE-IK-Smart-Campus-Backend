package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.ColumnName.UserConsultingDateColumnName.COLUMN_NAME_DURATION;
import static hu.unideb.smartcampus.shared.table.ColumnName.UserConsultingDateColumnName.COLUMN_NAME_REASON;
import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_USER_CONSULTING_DATE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User consulting date entity.
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_USER_CONSULTING_DATE)
public class UserConsultingDateEntity extends BaseEntity<Long> {

  /**
   * User.
   */
  @NotNull
  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  /**
   * Consulting date.
   */
  @NotNull
  @OneToOne(fetch = FetchType.LAZY)
  private ConsultingDateEntity consultingDate;

  /**
   * Reason of signin up.
   */
  @NotNull
  @Column(name = COLUMN_NAME_REASON)
  private String reason;

  /**
   * Reason of signin up.
   */
  @Column(name = COLUMN_NAME_DURATION)
  private String duration;

  /**
   * Constructs a UserConsultingDateEntity entity.
   */
  @Builder
  public UserConsultingDateEntity(final UserEntity user, final ConsultingDateEntity consultingDate,
      final String reason, final String duration) {
    this.user = user;
    this.consultingDate = consultingDate;
    this.reason = reason;
    this.duration = duration;
  }



}
