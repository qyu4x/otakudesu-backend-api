package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeListResponse;
import com.qirara.otakudesuapi.payload.response.LatestAnimeResponse;
import com.qirara.otakudesuapi.service.AnimeListService;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeListServiceImpl implements AnimeListService {

    private static final Logger log = LoggerFactory.getLogger(AnimeListServiceImpl.class);

    private ResourceConfig resourceConfig;

    public AnimeListServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<AnimeListResponse> getAll(Integer page) throws IOException {
        List<AnimeListResponse> animeListResponses = new ArrayList<>();
        String url = resourceConfig.getOtakudesuAnimeList().concat("page/").concat(String.valueOf(page)).concat("/");
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
