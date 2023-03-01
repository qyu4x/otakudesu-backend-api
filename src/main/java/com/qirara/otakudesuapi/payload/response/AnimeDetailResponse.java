package com.qirara.otakudesuapi.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnimeDetailResponse {

    private String synopsis;

    private String type;

    private String genre;

    private String status;

    private String totalEpisode;

    private String duration;

    private String broadcastDate;

    private List<ListAnimeEpisodeResponse> listEpisodeAnime;

}
