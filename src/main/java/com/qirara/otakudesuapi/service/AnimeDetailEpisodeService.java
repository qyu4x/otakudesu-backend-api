package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeDetailEpisodeResponse;

import java.io.IOException;

public interface AnimeDetailEpisodeService {
    AnimeDetailEpisodeResponse get(String linkDetailHref,  Integer episode) throws IOException;
}
