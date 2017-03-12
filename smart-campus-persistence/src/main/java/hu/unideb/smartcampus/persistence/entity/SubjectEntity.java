package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_SUBJECT;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Subject entity.
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = TABLE_NAME_SUBJECT)
public class SubjectEntity extends BaseEntity<Long> {

  /**
   * Name of the subject.
   */
  private String name;

  /**
   * Constructs a subject entity.
   */
  @Builder
  public SubjectEntity(final Long id, final String name) {
    super(id);
    this.name = name;
  }



}
