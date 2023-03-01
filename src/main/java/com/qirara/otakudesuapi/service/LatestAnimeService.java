package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.LatestAnimeResponse;

import java.io.IOException;
import java.util.List;

public interface LatestAnimeService {

    List<LatestAnimeResponse> getAll(Integer page) throws IOException;

}
