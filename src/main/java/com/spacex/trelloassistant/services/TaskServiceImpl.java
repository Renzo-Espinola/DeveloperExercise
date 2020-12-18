package com.spacex.trelloassistant.services;

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

    public TaskEntity save(TaskEntity taskEntity) {

        TypeTaskEntity typeTaskValid = new TypeTaskEntity();
        TaskRules taskRules = new TaskRules();
        typeTask = taskEntity.getTypeTaskEntity();

        Optional<TypeTaskEntity> typetaskOptional = typeTaskRepo.findById(typeTask.getId());
        if (typetaskOptional.isPresent()) {
            typeTaskValid = typetaskOptional.get();
        } else {
            logger.error("No task for this ID");
        }

        TaskEntity taskValid = taskRules.crearObjetoTaskValido(taskEntity,typeTaskValid);
        if (taskValid != null) {
            logger.info("Task Ok");
            return taskRepository.save(taskValid);
        } else {
            logger.error("Task Error");
            return taskRepository.save(taskEntity);
        }
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
