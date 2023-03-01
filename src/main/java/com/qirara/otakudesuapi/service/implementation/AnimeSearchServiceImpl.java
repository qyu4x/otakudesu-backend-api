package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeListResponse;
import com.qirara.otakudesuapi.service.AnimeSearchService;
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
public class AnimeSearchServiceImpl implements AnimeSearchService {

    private static final Logger log = LoggerFactory.getLogger(AnimeSearchServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public AnimeSearchServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<AnimeListResponse> get(String animeTitle) throws IOException {
        List<AnimeListResponse> animeListResponses = new ArrayList<>();
        String url = resourceConfig.getOtakudesuSearch().concat(animeTitle.trim());
        log.info("Fetching url {} ", url);

        Document document = Jsoup.connect(url).get();
        Elements listsLatestAnime = document.getElementsByClass("col-md-3 col-sm-3");
        listsLatestAnime.forEach(latestAnime -> {
            String imageURL = latestAnime.select("img").attr("src");
            String title = latestAnime.select("img").attr("alt");
            String linkHref = latestAnime.select("a").attr("href");
            log.info("image url {} ", imageURL);
            log.info("title {} ", imageURL);
            log.info("href {} ", imageURL);
            AnimeListResponse latestAnimeResponse = AnimeListResponse.builder()
                    .title(title)
                    .imageURL(imageURL)
                    .linkHref(linkHref)
                    .build();
            animeListResponses.add(latestAnimeResponse);
        });

        return animeListResponses;
    }
}
