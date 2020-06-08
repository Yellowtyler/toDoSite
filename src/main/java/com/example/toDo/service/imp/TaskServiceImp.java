package com.example.toDo.service.imp;

import com.example.toDo.entity.Task;
import com.example.toDo.repository.TaskRepository;
import com.example.toDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasks(Long id) {
        return taskRepository.getTasksByProjectId(id);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }

    @Override
    public void updateName(String name, Long id) {
         taskRepository.updateName(name, id);
    }

    @Override
    public void updateDescr(String descr, Long id) {
         taskRepository.updateDescr(descr, id);
    }

    @Override
    public void updateDate(Date date, Long id) {
         taskRepository.updateDate(date, id);
    }

    @Override
    public void updateType(Long id) {
         taskRepository.updateType(id);
    }

    @Override
    public void updateState(Long id) {
         taskRepository.updateState(id);
    }
}
