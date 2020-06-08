package com.example.toDo.rest;

import com.example.toDo.entity.Project;
import com.example.toDo.entity.Task;
import com.example.toDo.service.ProjectService;
import com.example.toDo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ProjectController.class, TaskController.class})
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private TaskService taskService;

    @Test
    void deleteProjectTest() throws Exception {
        Project project = new Project();
        project.setDate(new Date(2020,12,3));
        project.setDescr("safas");
        project.setName("day");
        project.setState(true);
        project.setUser(null);
        project.setId((long) 1);
        String project1 = new ObjectMapper().writeValueAsString(project);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/project/createProject")
                .content(project1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Task task = new Task();
        task.setDate(new Date(2020,12,3));
        task.setDescr("saffa");
        task.setName("asf");
        task.setState(true);
        task.setType("today");
        task.setProject(project);
        task.setId((long) 2);
        String task1 = new ObjectMapper().writeValueAsString(task);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/task/createTask")
                .content(task1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/project/deleteProject/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}