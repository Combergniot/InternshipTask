package com.internship.repositories;

import com.internship.model.AllegroProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllegroProjectRepository extends CrudRepository<AllegroProject, Long> {
}
