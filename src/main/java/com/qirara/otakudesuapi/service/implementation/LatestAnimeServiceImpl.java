package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.LatestAnimeResponse;
import com.qirara.otakudesuapi.service.LatestAnimeService;
import jakarta.annotation.PostConstruct;
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
public class LatestAnimeServiceImpl implements LatestAnimeService {

    private static final Logger log = LoggerFactory.getLogger(LatestAnimeServiceImpl.class);

    private ResourceConfig resourceConfig;

    public LatestAnimeServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<LatestAnimeResponse> getAll(Integer page) throws IOException {
        List<LatestAnimeResponse> latestAnimeResponses = new ArrayList<>();
        String url = resourceConfig.getOtakudesuHome().concat("page/").concat(String.valueOf(page)).concat("/");
        log.info("Fetching url {} ", url);

        Document document = Jsoup.connect(url).get();
        Elements listsLatestAnime = document.getElementsByClass("col-md-4 col-sm-4");
        listsLatestAnime.forEach(latestAnime -> {
            String imageURL = latestAnime.select("img").attr("src");
            String title = latestAnime.select("img").attr("alt");
            String linkHref =  new StringBuilder(latestAnime.select("a").attr("href").replace(resourceConfig.getOtakudesuSpecific(), "").replace("/", "")).insert(0, "/").toString();
            log.info("image url {} ", imageURL);
            log.info("title {} ", imageURL);
            log.info("href {} ", imageURL);
            LatestAnimeResponse latestAnimeResponse = LatestAnimeResponse.builder()
                    .title(title)
                    .imageURL(imageURL)
                    .linkHref(linkHref)
                    .build();
            latestAnimeResponses.add(latestAnimeResponse);
        });

        return latestAnimeResponses;
    }

}
