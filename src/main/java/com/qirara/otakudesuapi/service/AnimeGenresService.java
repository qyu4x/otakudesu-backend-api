package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeGenreResponse;
import com.qirara.otakudesuapi.payload.response.AnimeListResponse;

import java.io.IOException;
import java.util.List;

public interface AnimeGenresService {
   List<AnimeGenreResponse> getAll() throws IOException;

   List<AnimeListResponse> get(String genre, Integer page) throws IOException;
}
