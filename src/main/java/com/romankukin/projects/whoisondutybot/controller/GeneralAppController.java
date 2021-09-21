package com.romankukin.projects.whoisondutybot.controller;

import com.romankukin.projects.whoisondutybot.bot.WhoIsOnDutyBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GeneralAppController {

  private final WhoIsOnDutyBot bot;

  @RequestMapping(value = "/telegram", method = RequestMethod.POST)
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    log.info("/ root path triggered");
    return bot.onWebhookUpdateReceived(update);
  }

  // not working via ngrok
  @RequestMapping(value = "/health", method = RequestMethod.GET)
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok().body("OK");
  }
}
