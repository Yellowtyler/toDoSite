package com.example.toDo.service;

import com.example.toDo.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface TaskService {
    void createTask(Task task);

    List<Task> getAllTasks();

    List<Task> getTasks(Long id);

    Task getTask(Long id);

    void deleteTask(Long id);

    void updateName(String name, Long id);

    void updateDescr(String descr, Long id);

    void updateDate(Date date, Long id);

    void updateType(Long id);

    void updateState(Long id);
}
