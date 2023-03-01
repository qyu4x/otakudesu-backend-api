package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.OngoingAnimeResponse;
import com.qirara.otakudesuapi.service.OngoingAnimeService;
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
public class OngoingAnimeServiceServiceImpl implements OngoingAnimeService {

    private static final Logger log = LoggerFactory.getLogger(OngoingAnimeServiceServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public OngoingAnimeServiceServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }


    @Override
    public List<OngoingAnimeResponse> getAll(Integer page) throws IOException {
        List<OngoingAnimeResponse> ongoingAnimeResponses = new ArrayList<>();

        String url = resourceConfig.getOtakudesuOngoingList().concat("page/").concat(page.toString().concat("/"));
        Document jsoup =  Jsoup.connect(url).get();

        log.info("Fetching {} ", url);

        Elements listOngoingAnime = jsoup.getElementsByClass("col-md-3 col-sm-3");
        listOngoingAnime.stream().forEach(ongoingAnime -> {
            String imageUrl = ongoingAnime.select("img").attr("src");
            String title = ongoingAnime.select("img").attr("alt");
            String href = ongoingAnime.select("a").attr("href");

            log.info("image url {} ", title);
            log.info("title {} ", imageUrl);
            log.info("href {} ", href);

            OngoingAnimeResponse ongoingAnimeResponse = new OngoingAnimeResponse();
            ongoingAnimeResponse.setTitle(title);
            ongoingAnimeResponse.setImageURL(imageUrl);
            ongoingAnimeResponse.setLinkHref(href);

            ongoingAnimeResponses.add(ongoingAnimeResponse);
        });

        return ongoingAnimeResponses;
    }
}
