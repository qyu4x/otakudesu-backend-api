package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeListResponse;

import java.io.IOException;
import java.util.List;

public interface AnimeSearchService {
    List<AnimeListResponse> get(String title) throws IOException;
}
