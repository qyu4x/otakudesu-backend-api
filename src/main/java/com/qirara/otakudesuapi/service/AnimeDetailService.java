package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeDetailResponse;

import java.io.IOException;

public interface AnimeDetailService {
    AnimeDetailResponse getDetails(String linkDetailHref) throws IOException;
}
