package hu.unideb.smartcampus.service.ejabberd.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Create request can be used to create a group on a given host with specified attributes.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"PMD.SingularField"})
public class CreateGroupRequest extends BaseRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Group ID.
   */
  private final String group;

  /**
   * Group name.
   */
  private final String name;

  /**
   * Group description.
   */
  private final String description;

  /**
   * Displayed groups for the new group.
   */
  private final String display;

  /**
   * Constructor which is made for the @Builder for Lombok.
   *
   * @param host the host.
   * @param group the group id.
   * @param name the name of the group.
   * @param description the description of the group.
   * @param display the displayed groups.
   */
  @Builder
  public CreateGroupRequest(String host, String group, String name, String description,
      String display) {
    this.host = host;
    this.group = group;
    this.name = name;
    this.description = description;
    this.display = display;
  }



}
