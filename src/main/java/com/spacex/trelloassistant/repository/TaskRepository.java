package com.spacex.trelloassistant.repository;
import com.spacex.trelloassistant.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

}
