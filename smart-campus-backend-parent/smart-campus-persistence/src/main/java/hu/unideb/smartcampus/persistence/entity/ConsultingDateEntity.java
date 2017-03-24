package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.ColumnName.ConsultingDateColumnName.COLUMN_NAME_DATE;
import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_CONSULTING_DATE;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Consulting date entity.
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_CONSULTING_DATE)
public class ConsultingDateEntity extends BaseEntity<Long> {

  /**
   * Date of the consulting hours.
   */
  @NotNull
  @Size(min = 2, max = 36)
  @Column(name = COLUMN_NAME_DATE)
  private String dateInString;

  /**
   * From to date entity.
   */
  @Embedded
  private FromToDateEmbeddedEntity fromToDate;

  /**
   * Sum of students.
   */
  @Column(columnDefinition = "Decimal(10,2) default '0'")
  private Integer sum;

  /**
   * Constructs consulting date.
   */
  @Builder
  public ConsultingDateEntity(final Long id, final String date,
      final FromToDateEmbeddedEntity fromToDate, final Integer sum) {
    super(id);
    this.dateInString = date;
    this.fromToDate = fromToDate;
    this.sum = sum;
  }



}
