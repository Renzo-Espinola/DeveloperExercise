package com.spacex.trelloassistant.controllers;
import com.spacex.trelloassistant.entity.TaskEntity;
import com.spacex.trelloassistant.repository.TaskRepository;
import com.spacex.trelloassistant.rules.TaskRules;
import com.spacex.trelloassistant.services.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/Api Tasks")
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private ITaskService taskService;
    private TaskRules taskRules;
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskEntity taskEntity){
        String taskEntityDb =taskService.save(taskEntity);
        logger.info("New task Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskEntityDb);

    }
    @GetMapping
    @RequestMapping("listAll")
    public ResponseEntity<?>listAll() {return ResponseEntity.ok().body(taskService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<?>findIdTask(@PathVariable Long id) {
        Optional<TaskEntity> task = taskService.findBy(id);
        if (task.isEmpty()) {
            logger.error("ERROR TASKS NOT FOUND");
            return ResponseEntity.notFound().build();
        }
        logger.info("TASKS REQUESTED");
        return ResponseEntity.ok(task);
    }
    @PutMapping({"/{id}"})

    public ResponseEntity<?> editTask(@RequestBody TaskEntity taskEntity, @PathVariable Long id){
        Optional<TaskEntity> task1 = taskService.findBy(id);
        if(task1.isEmpty()){
            logger.error("ERROR TASKS NOT FOUND");
            return ResponseEntity.notFound().build();
        }
        TaskEntity taskEntityDb = task1.get();
        taskEntityDb.setCategoryEntity(taskEntity.getCategoryEntity());
        taskEntityDb.setDescription(taskEntity.getDescription());
        taskEntityDb.setTitle(taskEntity.getTitle());
        taskEntityDb.setTypeTaskEntity(taskEntity.getTypeTaskEntity());
        logger.info("TASKS UPDATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskEntityDb));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteBy(id);
        logger.warn("TASKS DELETED");
        return ResponseEntity.noContent().build();
    }
    

}
