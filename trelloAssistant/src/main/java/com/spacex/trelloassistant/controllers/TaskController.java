package com.spacex.trelloassistant.controllers;

import com.spacex.trelloassistant.models.entity.Task;
import com.spacex.trelloassistant.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.Optional;

@RestController
@RequestMapping("/Api Tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        Task taskDb=taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDb);
    }
    @GetMapping
    public ResponseEntity<?>listAll() {return ResponseEntity.ok().body(taskService.findall()); }

    @GetMapping("/{id}")
    public ResponseEntity<?>findIdTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findBy(id);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task.get());
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> editTask(@RequestBody Task task, @PathVariable Long id){
        Optional<Task> task1 = taskService.findBy(id);
        if(task1.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Task taskDb = task1.get();
        taskDb.setCategory(task.getCategory());
        taskDb.setDescription(task.getDescription());
        taskDb.setTitle(task.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskDb));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }
}
