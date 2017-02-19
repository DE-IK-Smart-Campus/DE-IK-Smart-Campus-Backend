package hu.unideb.smartcampus.persistence.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sample entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleEntity extends BaseEntity<Long> {

  /**
   * The name of the sample entity.
   */
  private String name;
}
