package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeDetailEpisodeResponse;
import com.qirara.otakudesuapi.service.AnimeDetailEpisodeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnimeDetailServiceImplEpisodeService implements AnimeDetailEpisodeService {

    private static final Logger log = LoggerFactory.getLogger(AnimeDetailServiceImplEpisodeService.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public AnimeDetailServiceImplEpisodeService(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }


    @Override
    public AnimeDetailEpisodeResponse get(String linkDetailHref, Integer episode) throws IOException {
        String url = resourceConfig.getOtakudesuMovie().concat("/").concat(linkDetailHref).concat("/?ep=").concat(episode.toString());
        Document document = Jsoup.connect(url).get();
        log.info("Fetching : {} ", url);
        AnimeDetailEpisodeResponse animeDetailEpisodeResponse = new AnimeDetailEpisodeResponse();

        String title = document.getElementsByClass("breadcrumb-item active").select("span[itemprop=name]").text();
        String streamingUrl =  document.getElementsByClass("player-embed").select("iframe").attr("src");

        log.info(title);
        log.info(streamingUrl);

        animeDetailEpisodeResponse.setTitle(title);
        animeDetailEpisodeResponse.setVideoStreamingUrl(streamingUrl);

        return animeDetailEpisodeResponse;
    }
}
