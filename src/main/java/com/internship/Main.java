package com.internship;

import com.internship.dataCollector.Scrapper;
import com.internship.model.AllegroProject;
import com.internship.services.AllegroProjectService;
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
    AllegroProjectService allegroProjectService;


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        scrapper.collectData();

        List<AllegroProject> allegroProjects = (List<AllegroProject>) allegroProjectService.list();
        Date latestDate = allegroProjects.stream()
                .map(AllegroProject::getDatetime)
                .max(Date::compareTo)
                .get();
        System.out.println("Nazwa repozytorium, którego kod był ostatnio modyfikowany to: ");
        allegroProjects.stream()
                .filter(x -> x.getDatetime() == latestDate)
                .map(x -> x.getTitle().toUpperCase())
                .forEach(System.out::println);
    }
}
