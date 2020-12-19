package com.spacex.trelloassistant.services;

import com.spacex.trelloassistant.exceptions.TaskException;
import com.spacex.trelloassistant.models.entity.TaskEntity;
import com.spacex.trelloassistant.models.entity.TypeTaskEntity;
import com.spacex.trelloassistant.models.repository.TaskRepository;
import com.spacex.trelloassistant.models.repository.TypeTaskRepository;
import com.spacex.trelloassistant.rules.TaskRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private TypeTaskEntity typeTask;
    @Autowired
    private TypeTaskRepository typeTaskRepo;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    @Override

    public String save(TaskEntity taskEntity) {
        String message = "";
        String messageError = "";
        TypeTaskEntity typeTaskValid = new TypeTaskEntity();
        TaskRules taskRules = new TaskRules();
        typeTask = taskEntity.getTypeTaskEntity();

        Optional<TypeTaskEntity> typetaskOptional = typeTaskRepo.findById(typeTask.getId());
        if (typetaskOptional.isPresent()) {
            typeTaskValid = typetaskOptional.get();
        } else {
            logger.error("No task for this ID");
        }
        Optional<TaskEntity> taskValid = Optional.empty();
        try {
            taskValid = taskRules.crearObjetoTaskValido(taskEntity, typeTaskValid);
        } catch (TaskException ex) {
            messageError = ex.getMessage();
        }
        if (taskValid.isPresent()) {
            logger.info("Task Ok");
            taskRepository.save(taskValid.get());
            message = "TASK SAVE";
        } else {
            logger.error("Task not Save");
            message = "TASK NOT SAVE "+ messageError;
        }
        return message;
    }

    @Override
    public Optional<TaskEntity> findBy(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteBy(Long id) {
        taskRepository.deleteById(id);
    }

}
