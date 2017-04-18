package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.ColumnName.InstructorColumnName.COLUMN_NAME_NAME;
import static hu.unideb.smartcampus.shared.table.ColumnName.InstructorColumnName.COLUMN_NAME_NEPTUN_IDENTIFIER;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instructor entity.
 */
@Data
@Embeddable
@NoArgsConstructor
public class InstructorEmbedded {

  /**
   * Name of the instructor.
   */
  @Column(name = COLUMN_NAME_NAME)
  private String name;

  /**
   * Neptun identifier of the user.
   */
  @Column(name = COLUMN_NAME_NEPTUN_IDENTIFIER)
  private String neptunIdentifier;

  /**
   * Constructs instructor entity.
   */
  @Builder
  public InstructorEmbedded(final String name,
      final String neptunIdentifier) {
    this.name = name;
    this.neptunIdentifier = neptunIdentifier;
  }


}
