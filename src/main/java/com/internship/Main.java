package com.internship;

import com.internship.dataCollector.Scrapper;
import com.internship.model.Project;
import com.internship.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    Scrapper scrapper;
    @Autowired
    ProjectService projectService;

//    set link to the repository on Github
    private final String LINK = "https://github.com/allegro";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        scrapper.collectData(LINK);

        List<Project> projects = (List<Project>) projectService.list();
        Date latestDate = projects.stream()
                .map(Project::getDatetime)
                .max(Date::compareTo)
                .get();
        System.out.println("Nazwa repozytorium, którego kod był ostatnio modyfikowany to: ");
        projects.stream()
                .filter(x -> x.getDatetime() == latestDate)
                .map(x -> x.getTitle().toUpperCase())
                .forEach(System.out::println);
    }
}
