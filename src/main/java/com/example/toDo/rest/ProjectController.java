package com.example.toDo.rest;


import com.example.toDo.entity.Project;
import com.example.toDo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/createProject")
    public ResponseEntity<String> createProject(@RequestBody Project project) {
      try {
          projectService.createProject(project);
      } catch (Exception exp) {
          return ResponseEntity.badRequest().body(exp.getMessage());
      }
      return  ResponseEntity.ok("Project's been created successfully!");
    }

    @GetMapping("/getAllProjects")
    public List<Project> getAllProjects() {
        return projectService.getAll();
    }

    @GetMapping("/getProjects/{id}")
    public List<Project> getUserProjects(@PathVariable Long id) {
        return projectService.getUserProjects(id);
    }

    @PutMapping("/updateState/{id}")
    public ResponseEntity<String> updateStateProject(@PathVariable Long id) {
      try {
          projectService.updateProjectState(id);
      } catch(Exception exp) {
          return ResponseEntity.badRequest().body(exp.getMessage());
      }
      return ResponseEntity.ok("Project's been updated successfully!");
    }

    @GetMapping("/getHiddenProjects/{id}")
    public List<Project> getUserHiddenProjects(@PathVariable Long id) {
        return projectService.getUserHiddenProjects(id);
    }

    @GetMapping("/getProject/{id}")
    public Project getProject(@PathVariable Long id){
        return projectService.getProject(id);
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Project's been deleted successfully!");
    }

    @PutMapping("/updateName/{id}")
    public ResponseEntity<String> updateName(@RequestBody String name, @PathVariable Long id) {
        try {
            projectService.updateName(name, id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }

        return ResponseEntity.ok("Project's been updated successfully!");
    }

    @PutMapping("/updateDescr/{id}")
    public ResponseEntity<String> updateDescr(@RequestBody String descr, @PathVariable Long id) {
        try {
            projectService.updateDescr(descr, id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }

        return ResponseEntity.ok("Project's been updated successfully!");
    }
   // pattern = "yyyy-MM-dd'T'HH:mm"
   // iso= DateTimeFormat.ISO.DATE_TIME
    @PutMapping("/updateDate/{id}")
    public ResponseEntity<String> updateDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date, @PathVariable Long id) {
        try {
            projectService.updateDate(date, id);
        } catch(Exception exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        return ResponseEntity.ok("Project's been updated successfully!");
    }

}
