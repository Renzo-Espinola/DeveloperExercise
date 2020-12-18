package com.spacex.trelloassistant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spacex.trelloassistant.controllers.TaskController;
import com.spacex.trelloassistant.models.entity.CategoryEntity;
import com.spacex.trelloassistant.models.entity.TaskEntity;
import com.spacex.trelloassistant.models.entity.TypeTaskEntity;
import com.spacex.trelloassistant.models.model.Category;
import com.spacex.trelloassistant.services.ITaskService;
import com.spacex.trelloassistant.services.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
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
