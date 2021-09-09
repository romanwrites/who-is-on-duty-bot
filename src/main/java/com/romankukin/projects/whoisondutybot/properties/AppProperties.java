package com.romankukin.projects.whoisondutybot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "who-is-on-duty-telegram-bot")
@Component
@Getter
@Setter
public class AppProperties {

  public String username;
  public String token;
  public String webhookUrl;
}
