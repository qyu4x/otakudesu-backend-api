package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.OngoingAnimeResponse;
import com.qirara.otakudesuapi.payload.response.OngoingAnimeV2Response;

import java.io.IOException;
import java.util.List;

public interface OngoingAnimeV2Service {
    List<OngoingAnimeV2Response> getAll(Integer page) throws IOException;
}
