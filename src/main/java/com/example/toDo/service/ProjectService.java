package com.example.toDo.service;

import com.example.toDo.entity.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    void createProject(Project project);

    List<Project> getUserProjects(Long id);

    List<Project> getUserHiddenProjects(Long id);

    Project getProject(Long id);

    void updateProjectState(Long id);

    void updateName(String name, Long id);

    void updateDescr(String descr, Long id);

    void updateDate(Date date, Long id);

    void deleteProject(Long id);

    List<Project> getAll();
}
