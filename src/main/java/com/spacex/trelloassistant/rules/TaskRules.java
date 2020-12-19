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

    TaskEntity validTask = new TaskEntity();

    public Optional<TaskEntity> crearObjetoTaskValido(TaskEntity taskEntity, TypeTaskEntity tipoTarea) throws TaskException {

        Optional<TaskEntity> saveOptionalTask = Optional.empty();

            if (applyRuleCategory(taskEntity, tipoTarea) == true
                    && applyRuleTitle(taskEntity, tipoTarea) == true
                    && applyRuleDescription(taskEntity, tipoTarea) == true) {
                saveOptionalTask = Optional.of(validTask);
            }

        return saveOptionalTask;
    }

    private boolean applyRuleCategory(TaskEntity taskEntity, TypeTaskEntity tipoTarea) throws TaskException {
        boolean response = true;
        if (tipoTarea.isHasCategory()) {
            if (taskEntity.getCategoryEntity() != null) {
                validTask.setCategoryEntity(taskEntity.getCategoryEntity());
                response = true;
            } else {
                response = false;
                throw new TaskException("Missing Category");

            }
        } else if (taskEntity.getCategoryEntity() != null) {
            response = false;
            throw new TaskException("No deberia tener categorua este tipo de tarea");
        }
        return response;
    }

    private boolean applyRuleTitle(TaskEntity taskEntity, TypeTaskEntity tipoTarea) throws TaskException {
        boolean response = true;
        if (tipoTarea.isHasTitle()) {
            if (taskEntity.getTitle() != null) {
                validTask.setTitle(taskEntity.getTitle());
                response = true;
            } else {
                response = false;

                throw new TaskException("Missing Title");

            }
        } else if (taskEntity.getTitle() != null) {
            response = false;
            throw new TaskException("No deberia tener titulo este tipo de tarea");
        }
        return response;
    }

    private boolean applyRuleDescription(TaskEntity taskEntity, TypeTaskEntity tipoTarea) throws TaskException {
        boolean response = true;
        if (tipoTarea.isHasDescription()) {
            if (taskEntity.getDescription() != null) {
                validTask.setDescription(taskEntity.getDescription());
                response = true;
            } else {
                response = false;
                throw new TaskException("falta descripcion");
            }
        } else if (taskEntity.getDescription() != null) {
            response = false;
            throw new TaskException("no deberia tener descripcion esta tarea");
        }
        return response;
    }

}


