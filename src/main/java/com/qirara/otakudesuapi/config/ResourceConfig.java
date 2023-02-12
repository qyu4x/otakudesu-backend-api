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

}
