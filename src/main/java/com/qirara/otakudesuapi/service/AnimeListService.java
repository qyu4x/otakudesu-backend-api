package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeListResponse;
import com.qirara.otakudesuapi.payload.response.LatestAnimeResponse;

import java.io.IOException;
import java.util.List;

public interface AnimeListService {
    List<AnimeListResponse> getAll(Integer page) throws IOException;
}
