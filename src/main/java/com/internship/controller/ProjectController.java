package com.internship.controller;

import com.internship.model.Project;
import com.internship.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    public Iterable<Project> getAll() {
        Iterable<Project> projectIterable = projectService.list();
        return projectIterable;
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project =
                projectService.findById(id);
        return project;
    }

    @GetMapping("/title={title}")
    public Optional<Project> getProjectByTitle(@PathVariable String title) {
        Optional<Project> project =
                projectService.findByTitle(title);
        return project;
    }


    @GetMapping("/language={programmingLanguage}")
    public Iterable<Project> getProjectByProgrammingLanguage(@PathVariable String programmingLanguage) {
        Iterable<Project> projectIterable =
                projectService.findByProgrammingLanguage(programmingLanguage);
        return projectIterable;
    }

}
