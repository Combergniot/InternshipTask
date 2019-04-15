package com.internship.controller;

import com.internship.model.AllegroProject;
import com.internship.services.AllegroProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class AllegroProjectController {

    @Autowired
    private AllegroProjectService allegroProjectService;

    @GetMapping("/all")
    public Iterable<AllegroProject> getAll() {
        Iterable<AllegroProject> projectIterable = allegroProjectService.list();
        return projectIterable;
    }

    @GetMapping("/{id}")
    public Optional<AllegroProject> getProjectById(@PathVariable Long id) {
        Optional<AllegroProject> allegroProject =
                allegroProjectService.findById(id);
        return allegroProject;
    }

}
