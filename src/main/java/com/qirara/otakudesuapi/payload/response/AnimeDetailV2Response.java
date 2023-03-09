package com.qirara.otakudesuapi.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnimeDetailV2Response {

    private String title;

    private String rating;

    private String synopsis;

    private String type;

    private String genre;

    private String status;

    private String totalEpisode;

    private String duration;

    private String broadcastDate;

    private String producer;

    private List<ListAnimeEpisodeResponse> listEpisodeAnime;

}
