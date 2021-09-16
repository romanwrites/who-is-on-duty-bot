package com.romankukin.projects.whoisondutybot.scheduled;

import com.romankukin.projects.whoisondutybot.properties.PingTaskProperties;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PreventIdlingErrorTask {

  private final PingTaskProperties pingTaskProperties;

  @Scheduled(fixedRateString = "#{@pingTaskProperties.getPeriod()}")
  public void ping() {
    try {
      URL url = new URL(pingTaskProperties.getUrl());
      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.connect();
      log.info("Ping {}, response status {}", url.getPath(), httpURLConnection.getResponseCode());
      httpURLConnection.disconnect();
    } catch (IOException e) {
      log.error("Ping Failed");
      e.printStackTrace();
    }
  }
}
