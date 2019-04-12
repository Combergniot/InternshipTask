package com.internship.dataCollector;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

public class ScrapperTest extends ScrapperSettings {

    Scrapper scrapper = new Scrapper();

    private String githubLink = "https://github.com/allegro";

    @Test
    public void shouldSayThatDataTimeIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForDateTime(element));
            System.out.println(scrapper.searchForDateTime(element));
        }
    }

    @Test
    public void shouldSayThatTitleIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForTitle(element));
            System.out.println(scrapper.searchForTitle(element));
        }
    }

    @Test
    public void shouldSayThatDescriptionIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForDescription(element));
            System.out.println(scrapper.searchForDescription(element));
        }
    }

    @Test
    public void shouldSayThatProgrammingLanguageIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForProgrammingLanguage(element));
            System.out.println(scrapper.searchForProgrammingLanguage(element));
        }
    }

    @Test
    public void shouldSayThatStarIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForStar(element));
            System.out.println(scrapper.searchForStar(element));
        }
    }

    @Test
    public void shouldSayThatForkIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForFork(element));
            System.out.println(scrapper.searchForFork(element));
        }
    }

    @Test
    public void shouldSayThatLinkIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForLink(element));
            System.out.println(scrapper.searchForLink(element));
        }
    }

    @Test
    public void shouldSayThatTopicListIsNotNull() throws Exception {
        Elements elements = scrapper.collectElements(githubLink);
        for (Element element : elements
        ) {
            Assert.assertNotNull(scrapper.searchForTopicList(element));
            System.out.println(scrapper.searchForTopicList(element));
        }
    }
}