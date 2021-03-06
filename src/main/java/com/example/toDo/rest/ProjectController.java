package com.example.toDo.rest;

import com.example.toDo.entity.Project;
import com.example.toDo.entity.User;
import com.example.toDo.repository.UserRepository;
import com.example.toDo.service.ProjectService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private static final DateTimeFormatter formatterCreate = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final DateTimeFormatter formatterUpdate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    @Autowired
    ProjectService projectService;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/createProject")
    public ResponseEntity<String> createProject(@RequestBody String projectJson) {
      try {
          JSONObject jsonObject = new JSONObject(projectJson);
          Optional<User> user = userRepository.findByLogin(jsonObject.getString("username"));
          Project project = new Project();
          project.setUser(user.get());
          project.setName(jsonObject.getString("name"));
          project.setDescr(jsonObject.getString("descr"));
          if(jsonObject.get("date") == JSONObject.NULL) {
              project.setDate(null);
          } else {
              String dateStr = jsonObject.getString("date");
              LocalDateTime localDateTime = null;
              if(!jsonObject.has("id")) {
                  dateStr = dateStr.substring(0, dateStr.length() - 3);
                  dateStr = dateStr.replace(",","");
                  localDateTime = LocalDateTime.parse(dateStr, formatterCreate);

              } else {
                  localDateTime = LocalDateTime.parse(dateStr, formatterUpdate);
              }
              project.setDate(localDateTime);
          }
          if(!jsonObject.has("id")) {
              project.setState(true);
              projectService.createProject(project);
              logger.info("Project " + project.getName() + " was successfully inserted!");
          } else {
              project.setState(jsonObject.getBoolean("state"));
              project.setId(Long.valueOf(String.valueOf(jsonObject.get("id"))));
              projectService.updateProject(project, project.getId());
              logger.info("Project " + project.getName() + " was successfully updated!");
          }

      } catch (Exception exp) {
          logger.error("Request /createProject failed. Error: " + exp.getMessage());
          return ResponseEntity.badRequest().body(exp.getMessage());
      }
      return  ResponseEntity.ok("Project's been created successfully!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllProjects")
    public List<Project> getAllProjects() {
        return projectService.getAll();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getProjects/{id}")
    public List<Project> getUserProjects(@PathVariable Long id) {
        return projectService.getUserProjects(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getHiddenProjects/{id}")
    public List<Project> getUserHiddenProjects(@PathVariable Long id) {
        return projectService.getUserHiddenProjects(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getProject/{id}")
    public Project getProject(@PathVariable Long id){
        return projectService.getProject(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
        } catch(Exception exp) {
            logger.error("Request /deleteProject failed. Error: " + exp.getMessage());
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Project's been deleted successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/updateState")
    public ResponseEntity<String> updateState(@RequestBody Long id) {
        try {
            projectService.updateProjectState(id);
        } catch(Exception exp) {
            logger.error("Request /updateState failed. Error: " + exp.getMessage());
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Project's been updated successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateName/{id}")
    public ResponseEntity<String> updateName(@RequestBody String name, @PathVariable Long id) {
        try {
            projectService.updateName(name, id);
        } catch(Exception exp) {
            logger.error("Request /updateName failed. Error: " + exp.getMessage());
            return ResponseEntity.badRequest().body(exp.getMessage());
        }

        return ResponseEntity.ok("Project's been updated successfully!");
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateDescr/{id}")
    public ResponseEntity<String> updateDescr(@RequestBody String descr, @PathVariable Long id) {
        try {
            projectService.updateDescr(descr, id);
        } catch(Exception exp) {
            logger.error("Request /updateDescr failed. Error: " + exp.getMessage());
            return ResponseEntity.badRequest().body(exp.getMessage());
        }

        return ResponseEntity.ok("Project's been updated successfully!");
    }
   // pattern = "yyyy-MM-dd'T'HH:mm"
   // iso= DateTimeFormat.ISO.DATE_TIME
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/updateDate/{id}")
    public ResponseEntity<String> updateDate(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime date, @PathVariable Long id) {
        try {
            projectService.updateDate(date, id);
        } catch(Exception exp) {
            logger.error("Request /updateDate failed. Error: " + exp.getMessage());
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Project's been updated successfully!");
    }

}
