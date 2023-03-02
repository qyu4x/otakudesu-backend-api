package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.exception.DataNotFoundException;
import com.qirara.otakudesuapi.payload.response.AnimeDetailResponse;
import com.qirara.otakudesuapi.payload.response.ListAnimeEpisodeResponse;
import com.qirara.otakudesuapi.service.AnimeDetailService;
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
public class AnimeDetailServiceServiceImpl implements AnimeDetailService {

    private static final Logger log = LoggerFactory.getLogger(AnimeDetailServiceServiceImpl.class);

    private  final ResourceConfig resourceConfig;

    public AnimeDetailServiceServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public AnimeDetailResponse getDetails(String linkDetailHref) throws IOException {
        //"https://otakudesu.to/movie/boruto-naruto-next-generations-u0wjvamebm/";
        if (linkDetailHref.isEmpty()) {
            throw new DataNotFoundException("Opps please enter valid URL");
        }

        Document document = Jsoup.connect(resourceConfig.getOtakudesuSpecific().concat(linkDetailHref.concat("/"))).get();
        log.info("Fetching : {} ", linkDetailHref);

        Elements detailAnime = document.getElementsByClass("article-body");
        String synopsis = detailAnime.select("p:contains(.)").first().text();
        String type = detailAnime.select("i[class=fa fa-minus]").get(0).parent().text().replace("Tipe : ", "");
        String genre = detailAnime.select("i[class=fa fa-minus]").get(1).parent().text().replace("Genre : ", "");
        String status = detailAnime.select("i[class=fa fa-minus]").get(2).parent().text().replace("Status : ", "");
        String totalEpisode = detailAnime.select("i[class=fa fa-minus]").get(3).parent().text().replace("Total Episode : ", "");
        String duration = detailAnime.select("i[class=fa fa-minus]").get(4).parent().text().replace("Durasi : ", "");
        String broadcastDate = detailAnime.select("i[class=fa fa-minus]").get(5).parent().text().replace("Tanggal Tayang : ", "");

        log.info(synopsis);
        log.info(type);
        log.info(genre);
        log.info(status);
        log.info(totalEpisode);
        log.info(duration);
        log.info(broadcastDate);

        List<ListAnimeEpisodeResponse> listAnimeEpisodeResponses = new ArrayList<>();
        Elements listEpisode = detailAnime.select("ul[class=daftarepi]").select("h4");
        listEpisode.stream().forEach(episode -> {
            String href = new StringBuilder(episode.select("a").attr("href").replace(resourceConfig.getOtakudesuSpecific(), "").replace("/", "")).insert(0, "/").toString();
            String episodeTitle = episode.select("a").text();

            ListAnimeEpisodeResponse listAnimeEpisodeResponse = new ListAnimeEpisodeResponse();
            listAnimeEpisodeResponse.setEpisodeTitle(episodeTitle);
            listAnimeEpisodeResponse.setHref(href);

            listAnimeEpisodeResponses.add(listAnimeEpisodeResponse);
        });


        AnimeDetailResponse animeDetailResponse = new AnimeDetailResponse();
        animeDetailResponse.setSynopsis(synopsis);
        animeDetailResponse.setType(type);
        animeDetailResponse.setGenre(genre);
        animeDetailResponse.setStatus(status);
        animeDetailResponse.setTotalEpisode(totalEpisode);
        animeDetailResponse.setDuration(duration);
        animeDetailResponse.setBroadcastDate(broadcastDate);
        animeDetailResponse.setListEpisodeAnime(listAnimeEpisodeResponses);

        return animeDetailResponse;
    }
}
