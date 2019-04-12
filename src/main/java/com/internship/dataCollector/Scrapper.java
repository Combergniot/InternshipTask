package com.internship.dataCollector;

import com.internship.model.AllegroProject;
import com.internship.services.AllegroProjectService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Scrapper extends ScrapperSettings {

    @Autowired
    AllegroProjectService allegroProjectService;

    private final String LINK = "https://github.com/allegro";

    //    TODO
    private List<String> collectLinks(String githubHomeLink) {
        List<String> links = new ArrayList<>();
        links.add("https://github.com/allegro");
        links.add("https://github.com/allegro?page=2");
        links.add("https://github.com/allegro?page=3");
        return links;
    }

    protected Elements collectElements(String githubLink) throws Exception {
        Document document = connectWith(githubLink);
        Elements elements = document.select("ul>li[itemprop = owns]");
        return elements;
    }

    public void collectData() throws Exception {
        List<String> paginationList = collectLinks(LINK);
        for (int i = 0; i < paginationList.size(); i++) {
            Elements elements = collectElements(paginationList.get(i));
            for (Element element : elements) {
                AllegroProject allegroProject = downloadProjectData(element);
                allegroProjectService.save(allegroProject);
                System.out.println(allegroProject);
            }
        }
    }

    protected AllegroProject downloadProjectData(Element element) {
        AllegroProject allegroProject = new AllegroProject();
        allegroProject.setTitle(searchForTitle(element));
        allegroProject.setDescription(searchForDescription(element));
        allegroProject.setProgrammingLanguage(searchForProgrammingLanguage(element));
        allegroProject.setStar(searchForStar(element));
        allegroProject.setFork(searchForFork(element));
        allegroProject.setLink(searchForLink(element));
        allegroProject.setTopicList(searchForTopicList(element));
        allegroProject.setDatetime(searchForDateTime(element));
        return allegroProject;
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
            dateTime = new SimpleDateFormat(DATE_FORMAT_PATTERN)
                    .parse(dateText);
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
