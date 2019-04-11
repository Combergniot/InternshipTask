package com.internship.services;

import com.internship.model.AllegroProject;
import com.internship.repositories.AllegroProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class AllegroProjectService {

    private final AllegroProjectRepository allegroProjectRepository;

    public AllegroProjectService(AllegroProjectRepository allegroProjectRepository) {
        this.allegroProjectRepository = allegroProjectRepository;
    }

    public Iterable<AllegroProject> list() {
        return allegroProjectRepository.findAll();
    }

    public AllegroProject save(AllegroProject allegroProject) {
        return allegroProjectRepository.save(allegroProject);
    }

}
