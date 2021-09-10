package com.romankukin.projects.whoisondutybot.service;

import com.romankukin.projects.whoisondutybot.model.TeamMember;
import com.romankukin.projects.whoisondutybot.repository.TeamRepository;
import java.util.Arrays;
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
    return String.join("\n", repository.listAllNames());
  }

  public String add(String teammates) {
    if (!StringUtils.hasText(teammates)) {
      return "Teammates list is empty";
    }

    int result = repository.add(Arrays.stream(teammates.replace(" ", "").split(";"))
        .map(name -> TeamMember.builder().name(name).build()).collect(Collectors.toList()));

    return "added " + result + " teammates";
  }

  public String remove(String name) {
    return "remove";
  }

  public String choose() {
    return "choose";
  }
}
