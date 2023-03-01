package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.OngoingAnimeResponse;

import java.io.IOException;
import java.util.List;

public interface OngoingAnimeService {
    List<OngoingAnimeResponse> getAll(Integer page) throws IOException;
}
