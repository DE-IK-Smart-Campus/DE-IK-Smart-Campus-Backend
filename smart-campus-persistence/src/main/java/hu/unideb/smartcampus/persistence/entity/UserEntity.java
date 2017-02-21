package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.table.ColumnName.UserColumnName.COLUMN_NAME_PASSWORD;
import static hu.unideb.smartcampus.shared.table.ColumnName.UserColumnName.COLUMN_NAME_ROLE;
import static hu.unideb.smartcampus.shared.table.ColumnName.UserColumnName.COLUMN_NAME_USERNAME;
import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_USER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import hu.unideb.smartcampus.shared.enumeration.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserEntity which represents the user.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = TABLE_NAME_USER, uniqueConstraints = @UniqueConstraint(columnNames = COLUMN_NAME_USERNAME))
public class UserEntity extends BaseEntity<Long> {

  /**
   * The username of the user.
   */
  @NotNull
  @Size(min = 2, max = 20)
  @Column(name = COLUMN_NAME_USERNAME)
  private String username;

  /**
   * The password of the user.
   */
  @NotNull
  @Size(min = 5, max = 250)
  @Column(name = COLUMN_NAME_PASSWORD)
  private String password;

  /**
   * The role of the user.
   */
  @NotNull
  @Column(name = COLUMN_NAME_ROLE)
  @Enumerated(EnumType.STRING)
  private Role role;

  /**
   * Builder pattern for creating user.
   */
  @Builder
  public UserEntity(final Long id, final String username, final String password, final Role role) {
    super(id);
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
