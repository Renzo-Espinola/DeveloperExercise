package com.spacex.trelloassistant.services;

import com.spacex.trelloassistant.models.entity.TaskEntity;

import java.util.Optional;

public interface ITaskService {

    Iterable<TaskEntity>findAll();
    TaskEntity save (TaskEntity taskEntity);
    Optional<TaskEntity>findBy(Long id);
    void deleteBy (Long id);
}
