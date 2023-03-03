package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeSearchV2Response;
import com.qirara.otakudesuapi.service.AnimeSearchV2Service;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeSearchV2ServiceImpl implements AnimeSearchV2Service {

    private static final Logger log = LoggerFactory.getLogger(AnimeSearchV2ServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public AnimeSearchV2ServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<AnimeSearchV2Response> get(String title) throws IOException {
        List<AnimeSearchV2Response> animeSearchV2Responses = new ArrayList<>();
        String url = String.format(resourceConfig.getOtakudesuSearchV2(), title);
        Document document = Jsoup.connect(url).get();

        log.info("Fetching {} ", url);

        Elements searchResult = document.getElementsByClass("chivsrc").select("li");
        searchResult.stream().forEach(search -> {
            String animeTitle = search.select("h2").select("a").text();
            String imageUrl = search.select("img").attr("src");
            String href = new StringBuilder(search.select("h2").select("a").attr("href").replace(resourceConfig.getOtakudesuDetailV2(), "").replace("/", "")).insert(0, "/").toString();
            String genres = search.select("div[class=set]").text().replace("Genres : ", "").split(":")[0];
            String status = search.select("div[class=set]").text().replace("Genres : ", "").split(":")[1].replace("Rating", "").replace(" ", "");
            String rating = search.select("div[class=set]").text().replace("Genres : ", "").split(":")[2].replace(" ", "");

            log.info(animeTitle);
            log.info(href);
            log.info(imageUrl);
            log.info(genres);
            log.info(status);
            log.info(rating);

            AnimeSearchV2Response animeSearchV2Response = new AnimeSearchV2Response();
            animeSearchV2Response.setTitle(animeTitle);
            animeSearchV2Response.setLinkHref(href);
            animeSearchV2Response.setImageURL(imageUrl);
            animeSearchV2Response.setGenre(genres);
            animeSearchV2Response.setStatus(status);
            animeSearchV2Response.setRating(rating);

            animeSearchV2Responses.add(animeSearchV2Response);


        });

        return animeSearchV2Responses;
    }
}
