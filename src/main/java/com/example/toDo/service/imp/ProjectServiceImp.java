package com.example.toDo.service.imp;

import com.example.toDo.entity.Project;
import com.example.toDo.repository.ProjectRepository;
import com.example.toDo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> getUserProjects(Long id) {
        return projectRepository.getUserProjectsById(id);
    }

    @Override
    public List<Project> getUserHiddenProjects(Long id) {
        return projectRepository.getUserHiddenProjectsById(id);
    }

    @Override
    public void updateProjectState(Long id) {
        projectRepository.updateStateProject(id);
    }

    @Override
    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public void updateName(String name, Long id) {
        projectRepository.updateName(name, id);
    }

    @Override
    public void updateDescr(String descr, Long id) {
        projectRepository.updateDescr(descr, id);
    }

    @Override
    public void updateDate(Date date, Long id) {
        projectRepository.updateDate(date, id);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteProject(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }


}
