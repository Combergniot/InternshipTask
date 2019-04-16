package com.internship.dataCollector;

import com.internship.model.Project;
import com.internship.services.ProjectService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class Scrapper extends ScrapperSettings {

    @Autowired
    ProjectService projectService;

    private static final Logger log = LoggerFactory.getLogger(Scrapper.class);

    protected int findLastPaginationNumber(String githubLink) throws IOException {
        Document document = connectWith(githubLink);
        String element = document.select("div.pagination>a")
                .prev()
                .last()
                .text();
        int lastPaginationNumber = Integer.valueOf(element);
        return lastPaginationNumber;
    }

    protected List<String> collectLinks(String githubLink) throws IOException {
        List<String> links = new ArrayList<>();
        int lastPaginationNumber = findLastPaginationNumber(githubLink);
        for (int i = 1; i <= lastPaginationNumber; i++) {
            links.add(githubLink + "?page=" + i);
        }
        log.info("The list of links has been created!");
        return links;
    }

    protected Elements collectElements(String githubLink) throws Exception {
        Document document = connectWith(githubLink);
        Elements elements = document.select("ul>li[itemprop = owns]");
        return elements;
    }

    public void collectData(String githubLink) throws Exception {
        List<String> paginationList = collectLinks(githubLink);
        for (int i = 0; i < paginationList.size(); i++) {
            Elements elements = collectElements(paginationList.get(i));
            for (Element element : elements) {
                Project project = downloadProjectData(element);
                projectService.save(project);
            }
        }
        log.info("All data were collected!");
    }

    protected Project downloadProjectData(Element element) {
        Project project = new Project();
        project.setTitle(searchForTitle(element));
        project.setDescription(searchForDescription(element));
        project.setProgrammingLanguage(searchForProgrammingLanguage(element));
        project.setStar(searchForStar(element));
        project.setFork(searchForFork(element));
        project.setLink(searchForLink(element));
        project.setTopicList(searchForTopicList(element));
        project.setDatetime(searchForDateTime(element));
        return project;
    }

    protected List<String> searchForTopicList(Element element) {
        List<String> topicList = new ArrayList<>();
        String topic = element.select("a.topic-tag").text();
        String[] parts = topic.split(" ");
        for (int i = 0; i < parts.length; i++) {
            String item = parts[i];
            topicList.add(item);
        }
        return topicList;
    }


    protected String searchForLink(Element element) {
        String link = element
                .select("[itemprop=name codeRepository]")
                .attr("abs:href");
        return link;
    }


    protected String searchForFork(Element element) {
        String fork = element
                .select("[href=/allegro/"
                        + searchForTitle(element)
                        + "/network]")
                .text();
        return fork;
    }


    protected String searchForStar(Element element) {
        String star = element
                .select("[href=/allegro/"
                        + searchForTitle(element)
                        + "/stargazers]")
                .text();
        return star;
    }

    //    consider save toLowerCase()
    protected String searchForProgrammingLanguage(Element element) {
        String programmingLanguage = element
                .select("[itemprop=programmingLanguage]")
                .text();
        return programmingLanguage;
    }

    //  Date sample: 2019-04-11T08:55:57Z
    protected Date searchForDateTime(Element element) {
        String dateText = element
                .select("[datetime]")
                .attr("datetime");
        Date dateTime = null;
        try {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(DATE_FORMAT_PATTERN);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            dateTime = simpleDateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }


    protected String searchForDescription(Element element) {
        String description = element
                .select("[itemprop=description]")
                .text();
        return description;

    }

    protected String searchForTitle(Element element) {
        String title = element
                .select("h3.wb-break-all")
                .text();
        return title;
    }

}
