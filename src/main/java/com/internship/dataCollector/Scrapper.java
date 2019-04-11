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

    private Elements collectElements(String githubLink) throws Exception {
        Document document = connectWith(githubLink);
        Elements elements = document.select("ul>li[itemprop = owns]");
        return elements;
    }

    //    TODO
    public void collectData() throws Exception {
        Elements elements = collectElements(LINK);
        for (Element element : elements
        ) {
            AllegroProject allegroProject = downloadProjectData(element);
            allegroProjectService.save(allegroProject);
            System.out.println(allegroProject);
        }
    }

    //  TODO
    public AllegroProject downloadProjectData(Element element) {
        AllegroProject allegroProject = new AllegroProject();
        allegroProject.setTitle(searchForTitle(element));
//        allegroProject.setDescription(searchForDescription(element));
//        allegroProject.setDatetime(searchForDateTime(element));
        allegroProject.setDatetime(searchForDateTime(element));
        return allegroProject;
    }


    //   2019-04-11T08:55:57Z
    public Date searchForDateTime(Element element) {
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

    // TODO
    public String searchForDescription(Element element) {
        String description = element.select("").text();
        return description;

    }

    public String searchForTitle(Element element) {
        String title = element.select("h3.wb-break-all").text();
        return title;
    }

}
