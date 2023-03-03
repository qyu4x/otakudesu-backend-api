package com.qirara.otakudesuapi.service.implementation;

import com.qirara.otakudesuapi.config.ResourceConfig;
import com.qirara.otakudesuapi.payload.response.ScheduleAnimeDetailResponse;
import com.qirara.otakudesuapi.payload.response.ScheduleAnimeResponse;
import com.qirara.otakudesuapi.service.ScheduleAnimeService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
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
public class ScheduleAnimeServiceImpl implements ScheduleAnimeService {

    private static final Logger log = LoggerFactory.getLogger(ScheduleAnimeServiceImpl.class);

    private ResourceConfig resourceConfig;

    @Autowired
    public ScheduleAnimeServiceImpl(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<ScheduleAnimeResponse> getAll() throws IOException {
        List<ScheduleAnimeResponse> scheduleAnimeResponses = new ArrayList<>();

        String url = resourceConfig.getOtakudesuScheduleV2();
        Document document = Jsoup.connect(url).get();
        log.info("Fetching {} ", url);

        Elements scheduleAnime = document.getElementsByClass("kglist321");

        scheduleAnime.stream().forEach(schedule -> {
            String broadcastDay = schedule.select("h2").text();
            Elements listAnime = schedule.select("ul").select("li");

            ScheduleAnimeResponse scheduleAnimeResponse = new ScheduleAnimeResponse();
            scheduleAnimeResponse.setBroadcastDay(broadcastDay);
            log.info(broadcastDay);

            List<ScheduleAnimeDetailResponse> scheduleAnimeDetailResponses = new ArrayList<>();
            listAnime.stream().forEach(list -> {
                String title = list.select("a").text();
                String linkHref = new StringBuilder(list.select("a").attr("href").replace(resourceConfig.getOtakudesuDetailV2(), "").replace("/", "")).insert(0, "/").toString();

                log.info(title);
                log.info(linkHref);

                ScheduleAnimeDetailResponse scheduleAnimeDetailResponse = new ScheduleAnimeDetailResponse();
                scheduleAnimeDetailResponse.setTitle(title);
                scheduleAnimeDetailResponse.setLinkHref(linkHref);

                scheduleAnimeDetailResponses.add(scheduleAnimeDetailResponse);
            });
            scheduleAnimeResponse.setAnime(scheduleAnimeDetailResponses);

            scheduleAnimeResponses.add(scheduleAnimeResponse);
        });

        return scheduleAnimeResponses;
    }
}
