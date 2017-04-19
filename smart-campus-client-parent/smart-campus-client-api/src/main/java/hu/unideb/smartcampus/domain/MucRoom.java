package hu.unideb.smartcampus.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * MUC room JSON definiton for Converse JS.
 */
@Data
public class MucRoom {

  @JsonProperty("jid")
  private String jid;
  
  @JsonProperty("nick")
  private String nick;

  /**
   * Constructor.
   */
  @Builder
  public MucRoom(String jid, String nick) {
    this.jid = jid;
    this.nick = nick;
  };
}
