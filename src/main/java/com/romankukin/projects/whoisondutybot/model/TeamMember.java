package com.romankukin.projects.whoisondutybot.model;

import java.util.Objects;
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
    return name + (username != null ? (" @" + username) : "");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TeamMember that = (TeamMember) o;

    if (!name.equals(that.name)) {
      return false;
    }
    return Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (username != null ? username.hashCode() : 0);
    return result;
  }
}
