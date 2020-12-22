package com.spacex.trelloassistant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@Slf4j
public class TrelloAssistantApplication{
   private final Logger logger = LoggerFactory.getLogger(TrelloAssistantApplication.class);

    public static void main(String[] args) { SpringApplication.run(TrelloAssistantApplication.class, args);
    }

}
