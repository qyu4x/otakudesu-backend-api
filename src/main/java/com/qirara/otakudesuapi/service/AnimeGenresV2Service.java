package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeGenreV2Response;

import java.io.IOException;
import java.util.List;

public interface AnimeGenresV2Service {
    List<AnimeGenreV2Response> getAll() throws IOException;
}
