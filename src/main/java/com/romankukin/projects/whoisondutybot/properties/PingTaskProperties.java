package com.romankukin.projects.whoisondutybot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "ping-task")
@Configuration
@Getter
@Setter
public class PingTaskProperties {

  private String url;
  private Long period;
}
