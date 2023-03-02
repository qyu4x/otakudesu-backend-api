package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.CompleteAnimeV2Response;
import com.qirara.otakudesuapi.service.CompleteAnimeV2Service;
import jakarta.annotation.PostConstruct;
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
public class CompleteAnimeV2ServiceImpl implements CompleteAnimeV2Service {

    private static final Logger log = LoggerFactory.getLogger(CompleteAnimeV2ServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public CompleteAnimeV2ServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<CompleteAnimeV2Response> getAll(Integer page) throws IOException {
        List<CompleteAnimeV2Response> completeAnimeV2Responses = new ArrayList<>();

        String url = resourceConfig.getOtakudesuCompleteV2().concat("page/").concat(page.toString()).concat("/");
        log.info("Fetching : {}", url);

        Document document = Jsoup.connect(url).get();
        Elements animeIsComplete = document.getElementsByClass("venz").select("li");

        animeIsComplete.stream().forEach(complete -> {
            String totalEpisode = complete.select("div[class=epz]").text();
            String rating = complete.select("div[class=detpost]").select("div[class=epztipe]").text();
            String title = complete.select("div[class=thumb]").select("h2").text();
            String imageUrl = complete.select("div[class=thumb]").select("div[class=thumbz]").select("img").attr("src");
            String lastUpdate = complete.select("div[class=newnime]").text();
            String href = new StringBuilder(complete.select("div[class=thumb]").select("a").attr("href").replace(resourceConfig.getOtakudesuDetailV2(), "").replace("/", "")).insert(0, "/").toString();

            log.info(totalEpisode);
            log.info(rating);
            log.info(title);
            log.info(imageUrl);
            log.info(lastUpdate);
            log.info(href);

            CompleteAnimeV2Response completeAnimeV2Response = new CompleteAnimeV2Response();
            completeAnimeV2Response.setTitle(title);
            completeAnimeV2Response.setRating(rating);
            completeAnimeV2Response.setTotalEpisode(totalEpisode);
            completeAnimeV2Response.setLastUpdate(lastUpdate);
            completeAnimeV2Response.setImageURL(imageUrl);
            completeAnimeV2Response.setLinkHref(href);

            completeAnimeV2Responses.add(completeAnimeV2Response);
        });

        return completeAnimeV2Responses;
    }
}
