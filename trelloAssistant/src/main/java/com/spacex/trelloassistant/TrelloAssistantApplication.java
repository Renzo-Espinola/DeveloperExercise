package com.spacex.trelloassistant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spacex.trelloassistant.controllers.TaskController;
import com.spacex.trelloassistant.services.ITaskService;
import com.spacex.trelloassistant.services.taskServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;


@SpringBootApplication
public class TrelloAssistantApplication{
    TaskController serviceTask = new TaskController();

    public static void main(String[] args) {
        SpringApplication.run(TrelloAssistantApplication.class, args);
    }

}
