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
        return links;
    }

    protected Elements collectElements(String githubLink) throws Exception {
        Document document = connectWith(githubLink);
        Elements elements = document.select("ul>li[itemprop = owns]");
        return elements;
    }

    //    TODO - linki 1,2,3
    public void collectData() throws Exception {
        Elements elements = collectElements(LINK);
        for (Element element : elements) {
            AllegroProject allegroProject = downloadProjectData(element);
            allegroProjectService.save(allegroProject);
            System.out.println(allegroProject);
        }
    }

    //  TODO
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

    //    TODO
    protected List<String> searchForTopicList(Element element) {
        List<String> topicList = new ArrayList<>();
        return topicList;
    }


    protected String searchForLink(Element element) {
        String link = element
                .select("[itemprop=name codeRepository]")
                .attr("abs:href");
        return link;
    }

    //    TODO
    protected int searchForFork(Element element) {
        int fork = 0;
        return fork;
    }

    //    TODO - svg
    protected int searchForStar(Element element) {
        int star = Integer.parseInt(element
                .getElementsByClass("octicon.octicon-star")
                .text());
        return star;
    }

    protected String searchForProgrammingLanguage(Element element) {
        String programmingLanguage = element.select("[itemprop=programmingLanguage]").text();
        return programmingLanguage;
    }


    //  Date sample: 2019-04-11T08:55:57Z
    protected Date searchForDateTime(Element element) {
        String dateText = element
                .select("[datetime]")
                .attr("datetime");
        Date dateTime = null;
        try {
            dateTime = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }


    protected String searchForDescription(Element element) {
        String description = element.select("[itemprop=description]").text();
        return description;

    }

    protected String searchForTitle(Element element) {
        String title = element.select("h3.wb-break-all").text();
        return title;
    }

}
