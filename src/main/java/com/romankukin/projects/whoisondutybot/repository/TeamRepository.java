package com.romankukin.projects.whoisondutybot.repository;

import com.romankukin.projects.whoisondutybot.model.TeamMember;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
@Slf4j
public class TeamRepository {

  private final static long NO_ID = 0L;
  private final static long FIRST_ID = 1L;
  private final static long INCREMENT_ID_VALUE = 1L;

  // todo make threadsafe class later
  private Map<Long, TeamMember> longToTeammate;
  private Map<TeamMember, Long> teammateToLong;
  private AtomicLong id;

  public TeamRepository() {
    longToTeammate = new ConcurrentHashMap<>();
    teammateToLong = new ConcurrentHashMap<>();
    id = new AtomicLong(NO_ID);
  }

  public synchronized int add(List<TeamMember> people) {
    for (TeamMember person : people) {
      long number = id.addAndGet(INCREMENT_ID_VALUE);
      longToTeammate.put(number, person);
      teammateToLong.put(person, number);
    }
    return people.size();
  }

  public List<String> listAllNames() {
    return longToTeammate.values().stream().map(TeamMember::getName).collect(Collectors.toList());
  }

  public int remove(List<TeamMember> people) {
    int removedCount = 0;
    for (TeamMember person : people) {
      if (teammateToLong.containsKey(person)) {
        long number = teammateToLong.get(person);
        longToTeammate.remove(number);
        teammateToLong.remove(person);
        ++removedCount;
      } else {
        log.warn("Tried to remove person {}. But no such person found", person);
      }
    }
    return removedCount;
  }

  public Optional<TeamMember> getTeammateByNumber(Long number) {
    if (longToTeammate.containsKey(number)) {
      return Optional.of(longToTeammate.get(number));
    }
    return Optional.empty();
  }

  public long teamSize() {
    return longToTeammate.size();
  }

  public long getLastId() {
    return teamSize();
  }

  public long getFirstId() {
    return longToTeammate.isEmpty() ? NO_ID : FIRST_ID;
  }

  public synchronized void clear() {
    longToTeammate.clear();
    teammateToLong.clear();
    id.set(NO_ID);
  }
}
