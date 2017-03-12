package hu.unideb.smartcampus.persistence.entity;

import static hu.unideb.smartcampus.shared.exclusion.FieldExclusion.EXCLUDE_PASSWORD;
import static hu.unideb.smartcampus.shared.table.ColumnName.UserColumnName.COLUMN_NAME_PASSWORD;
import static hu.unideb.smartcampus.shared.table.ColumnName.UserColumnName.COLUMN_NAME_ROLE;
import static hu.unideb.smartcampus.shared.table.ColumnName.UserColumnName.COLUMN_NAME_USERNAME;
import static hu.unideb.smartcampus.shared.table.TableName.TABLE_NAME_USER;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import hu.unideb.smartcampus.shared.enumeration.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * UserEntity which represents the user.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = EXCLUDE_PASSWORD)
@Entity
@Table(name = TABLE_NAME_USER,
    uniqueConstraints = @UniqueConstraint(columnNames = COLUMN_NAME_USERNAME))
@NamedQueries({
    @NamedQuery(name = "UserEntity.getSubjectsByUsername",
        query = "SELECT u.actualSubjects FROM UserEntity u WHERE u.username = ?1"),
    @NamedQuery(name = "UserEntity.getSubjectsByUserId",
        query = "SELECT u.actualSubjects FROM UserEntity u WHERE u.id = ?1")})
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
   * Actual semester subjects.
   */
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
  private Set<SubjectEntity> actualSubjects;

  /**
   * Builder pattern for creating user.
   */
  @Builder
  public UserEntity(final Long id, final String username, final String password, final Role role,
      final Set<SubjectEntity> actualSubjects) {
    super(id);
    this.username = username;
    this.password = password;
    this.role = role;
    this.actualSubjects = actualSubjects;
  }
}
