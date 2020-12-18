package com.spacex.trelloassistant.rules;

import com.spacex.trelloassistant.exceptions.TaskException;
import com.spacex.trelloassistant.models.entity.TaskEntity;
import com.spacex.trelloassistant.models.entity.TypeTaskEntity;
import com.spacex.trelloassistant.models.model.Task;
import com.spacex.trelloassistant.models.repository.TaskRepository;
import com.spacex.trelloassistant.models.repository.TypeTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TaskRules {
private static Logger logger = LoggerFactory.getLogger(TaskRules.class);
TaskException exception;
    public TaskEntity crearObjetoTaskValido(TaskEntity taskEntity, TypeTaskEntity tipoTarea) {

        TaskEntity saveTask = new TaskEntity();

        //CATEGORY
        if (tipoTarea.isHasCategory()) {
            if (taskEntity.getCategoryEntity() != null) {
                saveTask.setCategoryEntity(taskEntity.getCategoryEntity());
            } else {
                logger.warn("Missing Category",exception);
            }
        } else if (taskEntity.getCategoryEntity() != null) {
            logger.error("No deberia tener categorua este tipo de tarea",exception);
        }

        //TITLE
        if (tipoTarea.isHasTitle()) {
            if (taskEntity.getTitle() != null) {
                saveTask.setTitle(taskEntity.getTitle());
            } else {
                logger.warn("Missing Title",exception);
            }
        } else if (taskEntity.getTitle() != null) {
            logger.error("No deberia tener titulo este tipo de tarea",exception);
        }
        //DESCRIPTION
        if (tipoTarea.isHasDescription()) {

            if (taskEntity.getDescription() != null) {
                saveTask.setDescription(taskEntity.getDescription());
            } else {
                logger.warn("falta descripcion",exception);}

            }else if (taskEntity.getDescription() != null) {
                logger.error("no deberia tener descripcion esta tarea ",exception);
            }
            return saveTask;
        }
    }
