package com.romankukin.projects.whoisondutybot.bot;

import com.romankukin.projects.whoisondutybot.properties.AppProperties;
import com.romankukin.projects.whoisondutybot.service.TeamService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.Close;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Service
@Slf4j
public class WhoIsOnDutyBot extends SpringWebhookBot {

  private final AppProperties properties;
  private final TeamService service;
  private final static String NO_SUCH_COMMAND = "I don't know such command";
  private final static String BOT_COMMAND = "bot_command";

  public WhoIsOnDutyBot(AppProperties properties,
      TeamService service) {
    super(SetWebhook.builder().url(properties.getWebhookUrl()).build());
    this.properties = properties;
    this.service = service;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    log.info("update: {}", update);
    if (update.hasMessage()) {
      return handleMessage(update.getMessage());
    }
    return new Close();
//    return new SendMessage(String.valueOf(update.getMessage().getChatId()), "lolkekcheburek");
  }

  private BotApiMethod<?> handleMessage(Message message) {
    log.info("message: {}", message);

    Optional<List<MessageEntity>> entities = Optional.ofNullable(message.getEntities());
    if (entities.isPresent()) {
      Optional<MessageEntity> commandEntity = entities.get().stream()
          .filter(e -> BOT_COMMAND.equals(e.getType())).findFirst();
      if (commandEntity.isPresent()) {
        String[] command = message.getText().substring(commandEntity.get().getOffset()).split(" ");
        if (command.length >= 1 && command[0].contains("@")) {
          command[0] = command[0].substring(0, command[0].indexOf("@"));
        }
        return SendMessage.builder().text(handleCommand(command[0], command.length == 1 ? "" : command[1]))
            .chatId(message.getChatId().toString()).build();
      }
    }
    return generateDefaultMessage(message);
  }

  private static SendMessage generateDefaultMessage(Message message) {
    return SendMessage.builder().text(NO_SUCH_COMMAND)
        .chatId(message.getChatId().toString()).build();
  }

  private String handleCommand(String command, String params) {
    switch (command) {
      case "/help":
        return service.help();
      case "/list":
      case "/team":
        return service.team();
      case "/add":
        return service.add(params);
      case "/remove":
        return service.remove(params);
      case "/choose":
        return service.choose();
      default:
        return NO_SUCH_COMMAND;
    }
  }

  @Override
  public String getBotPath() {
    return properties.getUsername();
  }

  @Override
  public String getBotUsername() {
    return properties.getUsername();
  }

  @Override
  public String getBotToken() {
    return properties.getToken();
  }
}
