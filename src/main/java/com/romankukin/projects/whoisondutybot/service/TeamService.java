package com.romankukin.projects.whoisondutybot.service;

import com.romankukin.projects.whoisondutybot.model.TeamMember;
import com.romankukin.projects.whoisondutybot.repository.TeamRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@AllArgsConstructor
public class TeamService {

  private final TeamRepository repository;

  public String team() {
    List<String> names = repository.listAllNames();
    return names.isEmpty() ? "The team is empty" : String.join("\n", names);
  }

  public String add(String teammates) {
    if (!StringUtils.hasText(teammates)) {
      return "Teammates list is empty";
    }

    int result = repository.add(getTeammatesList(teammates));
    return "added " + result + " teammates";
  }

  public String remove(String teammates) {
    return "Remove command doesn't work now. Use /clear";
//    if (!StringUtils.hasText(teammates)) {
//      return "Teammates list is empty";
//    }
//
//    int result = repository.remove(getTeammatesList(teammates));
//    return "Removed " + result + " teammates";
  }

  private static List<TeamMember> getTeammatesList(String teammates) {
    return Arrays.stream(teammates.replace(" ", "").split(";"))
        .map(name -> TeamMember.builder().name(name).build()).collect(Collectors.toList());
  }

  public String choose() {
    long teamSize = repository.teamSize();
    if (teamSize == 0) {
      return "Team is empty, no one was randomly selected";
    }

    long teammateId = ThreadLocalRandom.current().nextLong(repository.getFirstId(), repository.getLastId() + 1);
    log.info("/ choose random teammate id: {}", teammateId);
    Optional<TeamMember> teammateByNumber = repository.getTeammateByNumber(teammateId);
    return teammateByNumber
        .map(teamMember -> teamMember.getName() + " is on duty today! 🥳")
        .orElse("something went wrong 🤔");
  }

  public String help() {
    return "`/add Hulk;Thor;SpiderMan;IronMan;Captain` to add people to list\n"
        + "`/team` to show team list\n"
        + "`/choose` to randomly select one person from a list\n";
  }

  public String clear() {
    repository.clear();
    return "List cleared";
  }
}
