package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.CompleteAnimeV2Response;
import com.qirara.otakudesuapi.payload.response.OngoingAnimeResponse;
import com.qirara.otakudesuapi.payload.response.OngoingAnimeV2Response;
import com.qirara.otakudesuapi.service.OngoingAnimeV2Service;
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
public class OngoingAnimeV2SereviceImpl implements OngoingAnimeV2Service {

    private static final Logger log = LoggerFactory.getLogger(OngoingAnimeV2SereviceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public OngoingAnimeV2SereviceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<OngoingAnimeV2Response> getAll(Integer page) throws IOException {
        List<OngoingAnimeV2Response> ongoingAnimeV2Responses = new ArrayList<>();

        String url = resourceConfig.getOtakudesuOngoingV2().concat("page/").concat(page.toString()).concat("/");
        log.info("Fetching : {}", url);

        Document document = Jsoup.connect(url).get();
        Elements animeIsComplete = document.getElementsByClass("venz").select("li");

        animeIsComplete.stream().forEach(complete -> {
            String currentEpisode = complete.select("div[class=epz]").text();
            String releaseDay = complete.select("div[class=detpost]").select("div[class=epztipe]").text();
            String title = complete.select("div[class=thumb]").select("h2").text();
            String imageUrl = complete.select("div[class=thumb]").select("div[class=thumbz]").select("img").attr("src");
            String lastUpdate = complete.select("div[class=newnime]").text();
            String href = new StringBuilder(complete.select("div[class=thumb]").select("a").attr("href").replace(resourceConfig.getOtakudesuDetailV2(), "").replace("/", "")).insert(0, "/").toString();

            log.info(currentEpisode);
            log.info(releaseDay);
            log.info(title);
            log.info(imageUrl);
            log.info(lastUpdate);
            log.info(href);

            OngoingAnimeV2Response ongoingAnimeV2Response = new OngoingAnimeV2Response();
            ongoingAnimeV2Response.setTitle(title);
            ongoingAnimeV2Response.setReleaseDay(releaseDay);
            ongoingAnimeV2Response.setCurrentEpisode(currentEpisode);
            ongoingAnimeV2Response.setLastUpdate(lastUpdate);
            ongoingAnimeV2Response.setImageURL(imageUrl);
            ongoingAnimeV2Response.setLinkHref(href);

            ongoingAnimeV2Responses.add(ongoingAnimeV2Response);
        });

        return ongoingAnimeV2Responses;

    }
}
