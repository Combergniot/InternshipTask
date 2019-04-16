package com.internship.services;

import com.internship.model.Project;
import com.internship.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Iterable<Project> list() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project;
    }

    public Optional<Project> findByTitle(String title) {
        Optional<Project> project = projectRepository.findByTitle(title);
        return project;
    }

    public Iterable<Project> findByProgrammingLanguage(String programmingLanguage) {
        Iterable<Project> projectIterable = projectRepository.findByProgrammingLanguage(programmingLanguage);
        return projectIterable;
    }
}
