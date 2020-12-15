package com.spacex.trelloassistant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spacex.trelloassistant.services.ITaskService;
import com.spacex.trelloassistant.services.TaskServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class TrelloAssistantApplication{
    ITaskService serviceTask = new TaskServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(TrelloAssistantApplication.class, args);
    }

}
