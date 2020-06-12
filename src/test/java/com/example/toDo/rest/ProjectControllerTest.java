package com.example.toDo.rest;

import com.example.toDo.entity.Project;
import com.example.toDo.entity.Task;
import com.example.toDo.service.ProjectService;
import com.example.toDo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        project.setDate(LocalDateTime.of(2020,12,3,12,00).truncatedTo(ChronoUnit.SECONDS));
        project.setDescr("safas");
        project.setName("day");
        project.setState(true);
        project.setUser(null);
        project.setId((long) 1);
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        objectMapper.setDateFormat(dateTimeFormatter);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        String project1 = objectMapper.writeValueAsString(project);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/project/createProject")
                .content(project1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/project/getProject/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        Task task = new Task();
        task.setDate(LocalDateTime.of(2020,12,3,12,00));
        task.setDescr("saffa");
        task.setName("asf");
        task.setState(true);
        task.setType("today");
        task.setProject(project);
        task.setId((long) 2);
        String task1 = objectMapper.writeValueAsString(task);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/task/createTask")
                .content(task1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/project/deleteProject/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}