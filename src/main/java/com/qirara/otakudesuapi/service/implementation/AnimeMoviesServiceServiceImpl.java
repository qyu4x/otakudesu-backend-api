package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeMoviesResponse;
import com.qirara.otakudesuapi.service.AnimeMoviesService;
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
public class AnimeMoviesServiceServiceImpl implements AnimeMoviesService {

    private static final Logger log = LoggerFactory.getLogger(AnimeMoviesServiceServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public AnimeMoviesServiceServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<AnimeMoviesResponse> getAll(Integer page) throws IOException {
        List<AnimeMoviesResponse> animeMoviesResponses = new ArrayList<>();

        String url = resourceConfig.getOtakudesuMovie().concat("page/").concat(page.toString()).concat("/");
        Document document = Jsoup.connect(url).get();

        log.info("Fetching : {}", url);
        Elements listAnimeMovies = document.getElementsByClass("col-md-3 col-sm-3");

        listAnimeMovies.stream().forEach(animeMovie -> {
            String imageUrl = animeMovie.select("img").attr("src");
            String title = animeMovie.select("img").attr("alt");
            String href = animeMovie.select("a").attr("href");

            log.info("image url {} ", title);
            log.info("title {} ", imageUrl);
            log.info("href {} ", href);

            AnimeMoviesResponse animeMoviesResponse = new AnimeMoviesResponse();
            animeMoviesResponse.setTitle(title);
            animeMoviesResponse.setImageURL(imageUrl);
            animeMoviesResponse.setLinkHref(href);

            animeMoviesResponses.add(animeMoviesResponse);
        });

        return animeMoviesResponses;
    }
}
