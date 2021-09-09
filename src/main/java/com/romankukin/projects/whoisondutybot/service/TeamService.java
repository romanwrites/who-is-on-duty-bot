package com.romankukin.projects.whoisondutybot.service;

import com.romankukin.projects.whoisondutybot.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TeamService {

  private final TeamRepository repository;

  public String team() {
    return String.join("\n", repository.listAllNames());
  }

  public String add() {
    return "add";
  }

  public String remove() {
    return "remove";
  }

  public String choose() {
    return "choose";
  }
}
