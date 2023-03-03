package com.qirara.otakudesuapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ResourceConfig {

    @Value("${otakudesu.home}")
    private String otakudesuHome;

    @Value("${otakudesu.anime-list}")
    private String otakudesuAnimeList;

    @Value("${otakudesu.ongoing-list}")
    private String otakudesuOngoingList;

    @Value("${otakudesu.movie}")
    private String otakudesuMovie;

    @Value("${otakudesu.genre}")
    private String otakudesuGenre;

    @Value("${otakudesu.genre-detail}")
    private String otakudesuGenreDetail;

    @Value("${otakudesu.specific}")
    private String otakudesuSpecific;

    @Value("${otakudesu.search}")
    private String otakudesuSearch;

    @Value("${otakudesu.v2.complete-anime}")
    private String otakudesuCompleteV2;

    @Value("${otakudesu.v2.detail}")
    private String otakudesuDetailV2;

    @Value("${otakudesu.v2.ongoing-anime}")
    private String otakudesuOngoingV2;

    @Value("${otakudesu.v2.schedule}")
    private String otakudesuScheduleV2;

}
