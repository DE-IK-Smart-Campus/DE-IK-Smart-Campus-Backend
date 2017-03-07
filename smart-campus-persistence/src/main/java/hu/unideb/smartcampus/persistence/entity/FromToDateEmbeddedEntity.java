package hu.unideb.smartcampus.persistence.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * From to date embedded entity.
 */
@Data
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FromToDateEmbeddedEntity {

  /**
   * From date.
   */
  @Column(name = "START_DATE")
  private Timestamp fromDate;

  /**
   * To date.
   */
  @Column(name = "END_DATE")
  private Timestamp toDate;

  /**
   * Constructs a FromToDateEmbeddedEntity entity.
   */
  @Builder
  public FromToDateEmbeddedEntity(final Timestamp fromDate, final Timestamp toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }



}
