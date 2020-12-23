package com.spacex.trelloassistant.services;

import com.spacex.trelloassistant.exceptions.ObjectNotFoundException;
import com.spacex.trelloassistant.exceptions.TaskException;
import com.spacex.trelloassistant.entity.TaskEntity;
import com.spacex.trelloassistant.entity.TypeTaskEntity;

import com.spacex.trelloassistant.repository.TaskRepository;
import com.spacex.trelloassistant.repository.TypeTaskRepository;
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
            throw new ObjectNotFoundException("No task for this ID");
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
            message = "TASK NOT SAVE " + messageError;
        }
        return message;
    }

    @Override
    public Optional<TaskEntity> findBy(Long id) {
        Optional<TaskEntity> message= null;
        try {
            if (id != null) {
                message= taskRepository.findById(id);

            }
        } catch (ObjectNotFoundException ob) {
            logger.error(ob.getMessage());
        }
        return message;
    }

    @Override
    public void deleteBy(Long id) {
        String message= null;
        try {
            if (id != null) {
                taskRepository.deleteById(id);
                message="ID FOUND";
            }

        } catch (ObjectNotFoundException ob) {
            String messageObError = ob.getMessage();
        }
        System.out.println(message);
    }

}
