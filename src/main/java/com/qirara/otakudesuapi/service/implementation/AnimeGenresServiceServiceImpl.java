package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.AnimeGenreResponse;
import com.qirara.otakudesuapi.payload.response.AnimeListResponse;
import com.qirara.otakudesuapi.service.AnimeGenresService;
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
public class AnimeGenresServiceServiceImpl implements AnimeGenresService {

    private static final Logger log = LoggerFactory.getLogger(AnimeGenresServiceServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public AnimeGenresServiceServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<AnimeGenreResponse> getAll() throws IOException {
        List<AnimeGenreResponse> animeGenreResponses = new ArrayList<>();

        String url = resourceConfig.getOtakudesuGenre();
        Document document = Jsoup.connect(url).get();
        log.info("Fetching : {} ", url);

        Elements listAnimeGenre = document.getElementsByClass("taxindex").select("li");
        listAnimeGenre.stream().forEach(animeGenre -> {
            String genre = animeGenre.select("a").select("span").text();
            String total = animeGenre.select("a").select("i").text();
            String href = animeGenre.select("a").attr("href");

            log.info("genre :  {} ", genre);
            log.info("total :  {} ", total);
            log.info("href :  {} ", href);

            AnimeGenreResponse animeGenreResponse = new AnimeGenreResponse();
            animeGenreResponse.setGenre(genre);
            animeGenreResponse.setTotal(total);
            animeGenreResponse.setLinkHref(href);

            animeGenreResponses.add(animeGenreResponse);

        });
        
        return animeGenreResponses;
    }

    @Override
    public List<AnimeListResponse> get(String genre, Integer page) throws IOException {
        List<AnimeListResponse> animeListResponses = new ArrayList<>();
        String url = resourceConfig.getOtakudesuGenreDetail().concat(genre).concat("/page/").concat(String.valueOf(page)).concat("/");
        log.info("Fetching url {} ", url);

        Document document = Jsoup.connect(url).get();
        Elements listGenretAnime = document.getElementsByClass("col-md-3 col-sm-3");
        listGenretAnime.forEach(genreAnime -> {
            String imageURL = genreAnime.select("img").attr("data-src");
            String title = genreAnime.select("img").attr("alt");
            String linkHref =  new StringBuilder(genreAnime.select("a").attr("href").replace(resourceConfig.getOtakudesuSpecific(), "").replace("/", "")).insert(0, "/").toString();
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
