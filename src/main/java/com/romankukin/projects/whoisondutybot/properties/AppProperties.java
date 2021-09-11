package com.romankukin.projects.whoisondutybot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "who-is-on-duty-telegram-bot")
@Configuration
@Getter
@Setter
public class AppProperties {

  private String username;
  private String token;
  private String webhookUrl;
}
