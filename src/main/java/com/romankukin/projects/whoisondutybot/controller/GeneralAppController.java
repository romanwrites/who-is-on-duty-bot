package com.romankukin.projects.whoisondutybot.controller;

import com.romankukin.projects.whoisondutybot.bot.WhoIsOnDutyBot;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GeneralAppController {

  private final WhoIsOnDutyBot bot;

  @PostMapping("/")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    log.info("controller log");
    return bot.onWebhookUpdateReceived(update);
  }
}
