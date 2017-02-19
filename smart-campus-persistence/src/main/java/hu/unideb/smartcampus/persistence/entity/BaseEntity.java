package hu.unideb.smartcampus.persistence.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base entity which provides the primary key.
 * @param <T> the type of the primary key.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseEntity<T extends Serializable> implements Serializable {

  private static final long serialVersionUID = -862719550032921202L;

  /**
   * Id as primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected T id;
}
