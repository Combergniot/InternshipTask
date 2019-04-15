package com.internship.repositories;

import com.internship.model.AllegroProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllegroProjectRepository extends CrudRepository<AllegroProject, Long> {

    Optional<AllegroProject> findById(Long id);
}
