package com.spacex.trelloassistant.models.repository;

import com.spacex.trelloassistant.models.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

}
