package com.internship.repositories;

import com.internship.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> findById(Long id);

    Optional<Project> findByTitle(String title);

    Iterable<Project> findByProgrammingLanguage(String programmingLanguage);

}
