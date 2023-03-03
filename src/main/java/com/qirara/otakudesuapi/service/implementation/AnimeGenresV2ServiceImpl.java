package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeGenreV2Response;
import com.qirara.otakudesuapi.service.AnimeGenresV2Service;
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
public class AnimeGenresV2ServiceImpl implements AnimeGenresV2Service {
    
    private static final Logger log = LoggerFactory.getLogger(AnimeGenresV2ServiceImpl.class);
    
    private ResourceConfig resourceConfig;

    public AnimeGenresV2ServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<AnimeGenreV2Response> getAll() throws IOException {
        List<AnimeGenreV2Response> animeGenreV2Responses = new ArrayList<>();
        String url = resourceConfig.getOtakudesuGenreListV2();
        Document document = Jsoup.connect(url).get();
        
        log.info("Fetching {} ", url);
        Elements genreList = document.getElementsByClass("genres").select("li").select("a");
        genreList.stream().forEach(genre -> {
            String title = genre.text();
            String href = new StringBuilder(genre.attr("href").replace("/genres", "").replace("/", "")).insert(0, "/").toString();

            log.info(title);
            log.info(href);

            AnimeGenreV2Response animeGenreV2Response = new AnimeGenreV2Response();
            animeGenreV2Response.setGenre(title);
            animeGenreV2Response.setLinkHref(href);

            animeGenreV2Responses.add(animeGenreV2Response);

        });
        return animeGenreV2Responses;
    }
}
