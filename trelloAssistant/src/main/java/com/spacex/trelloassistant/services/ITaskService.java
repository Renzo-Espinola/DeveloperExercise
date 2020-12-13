package com.spacex.trelloassistant.services;

import com.spacex.trelloassistant.models.entity.Task;

import java.util.Optional;

public interface ITaskService {

    Iterable<Task>findall();
    Task save (Task task);
    Optional<Task>findBy(Long id);
    void deleteBy (Long id);
}
