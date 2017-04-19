package hu.unideb.smartcampus.webservice.api.ejabberd.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MUC member domain class.
 */
@Data
@NoArgsConstructor
public class MucMember {

  /**
   * JID of the user.
   */
  @JsonProperty("jid")
  private String jid;

  /**
   * Nickname of the user.
   */
  @JsonProperty("nick")
  private String nick;

  /**
   * Role of the user, for example:moderator, or participant.
   */
  @JsonProperty("role")
  private String role;

  /**
   * Constructs an instance.
   */
  @Builder
  public MucMember(String jid, String nick, String role) {
    this.jid = jid;
    this.nick = nick;
    this.role = role;
  }



}
