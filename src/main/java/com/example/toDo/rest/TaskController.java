package com.example.toDo.rest;

import com.example.toDo.entity.Task;
import com.example.toDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/createTask")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        try {
            taskService.createTask(task);
        } catch (Exception exp) {
            return  ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been successfully created!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getTasks/{id}")
    public List<Task> getTasks(@PathVariable Long id){
        return taskService.getTasks(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getTask/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been deleted successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateName/{id}")
    public ResponseEntity<String> updateName(@RequestBody String name, @PathVariable Long id) {
        try {
            taskService.updateName(name, id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been updated successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateDescr/{id}")
    public ResponseEntity<String> updateDescr(@RequestBody String descr, @PathVariable Long id) {
        try {
            taskService.updateDescr(descr, id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been updated successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateDate/{id}")
    public ResponseEntity<String> updateDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date, @PathVariable Long id) {
        try {
            taskService.updateDate(date, id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been updated successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateState/{id}")
    public ResponseEntity<String> updateState(@PathVariable Long id) {
        try {
            taskService.updateState(id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been updated successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateType/{id}")
    public ResponseEntity<String> updateType(@PathVariable Long id) {
        try {
            taskService.updateType(id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Task has been updated successfully!");
    }
}
