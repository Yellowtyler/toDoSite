package com.example.toDo.service;

import com.example.toDo.entity.Project;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectService {
    void createProject(Project project);

    List<Project> getUserProjects(Long id);

    List<Project> getUserHiddenProjects(Long id);

    Project getProject(Long id);

    void updateProjectState(Long id);

    void updateName(String name, Long id);

    void updateDescr(String descr, Long id);

    void updateDate(LocalDateTime date, Long id);

    void deleteProject(Long id);

    void updateProject(Project project, Long id);

    List<Project> getAll();
}
