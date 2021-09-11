package com.romankukin.projects.whoisondutybot.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TeamMember {

  @NotEmpty
  @NotNull
  private String name;
  private String username;

  @Override
  public String toString() {
    return "@" + username;
  }
}
