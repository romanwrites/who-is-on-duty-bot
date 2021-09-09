package com.romankukin.projects.whoisondutybot.repository;

import com.romankukin.projects.whoisondutybot.model.TeamMember;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@AllArgsConstructor
@Repository
public class TeamRepository {

  // todo make threadsafe class later
  private Map<Integer, TeamMember> map = new ConcurrentHashMap<>();

  public List<String> listAllNames() {
    return List.of("Iron Man", "Spider Man", "Dr. Strange", "Thor", "Hulk");
  }
}
