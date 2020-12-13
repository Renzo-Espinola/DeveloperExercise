package com.spacex.trelloassistant.services;

import com.spacex.trelloassistant.models.entity.Task;
import com.spacex.trelloassistant.models.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class taskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Task> findall() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findBy(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteBy(Long id) {
    taskRepository.deleteById(id);
    }
}
