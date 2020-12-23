package com.spacex.trelloassistant.services;

import com.spacex.trelloassistant.entity.TaskEntity;
import com.spacex.trelloassistant.exceptions.ObjectNotFoundException;


import java.util.Optional;

public interface ITaskService {

    Iterable<TaskEntity>findAll();
    String save (TaskEntity taskEntity);
    Optional<TaskEntity> findBy(Long id);
    void deleteBy (Long id);
}
