package hu.unideb.smartcampus.persistence.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * From to date embedded entity.
 */
@Data
@Embeddable
@NoArgsConstructor
public class FromToDateEmbeddedEntity {

  /**
   * From date.
   */
  @NotNull
  @Column(name = "START_DATE")
  private LocalDateTime fromDate;

  /**
   * To date.
   */
  @NotNull
  @Column(name = "END_DATE")
  private LocalDateTime toDate;

  /**
   * Constructs a FromToDateEmbeddedEntity entity.
   */
  @Builder
  public FromToDateEmbeddedEntity(final LocalDateTime fromDate, final LocalDateTime toDate) {
    this.fromDate = fromDate;
    this.toDate = toDate;
  }



}
