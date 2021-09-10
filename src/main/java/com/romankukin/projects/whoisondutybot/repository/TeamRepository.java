package com.romankukin.projects.whoisondutybot.repository;

import com.romankukin.projects.whoisondutybot.model.TeamMember;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
public class TeamRepository {

  // todo make threadsafe class later
  private Map<Long, TeamMember> map;
  private AtomicLong id;

  public TeamRepository() {
    map = new ConcurrentHashMap<>();
    id = new AtomicLong(0);
  }

  public int add(List<TeamMember> people) {
    for (TeamMember person : people) {
      map.put(id.addAndGet(1), person);
    }
    return people.size();
  }

  public List<String> listAllNames() {
    return map.values().stream().map(TeamMember::getName).collect(Collectors.toList());
  }
}
