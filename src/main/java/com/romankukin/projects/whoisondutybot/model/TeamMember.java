package com.romankukin.projects.whoisondutybot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamMember {

  private String name;
  private String username;

  @Override
  public String toString() {
    return "@" + username;
  }
}
