package com.romankukin.projects.whoisondutybot.bot;

import com.romankukin.projects.whoisondutybot.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Service
@Slf4j
public class WhoIsOnDutyBot extends SpringWebhookBot {

  private final AppProperties properties;

  public WhoIsOnDutyBot(AppProperties properties) {
    super(SetWebhook.builder().url(properties.webhookUrl).build());
    this.properties = properties;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    System.out.println(update.getMessage().getText());
    return new SendMessage(String.valueOf(update.getMessage().getChatId()), "lolkekcheburek");
  }

  @Override
  public String getBotPath() {
    return properties.username;
  }

  @Override
  public String getBotUsername() {
    return properties.username;
  }

  @Override
  public String getBotToken() {
    return properties.token;
  }
}
