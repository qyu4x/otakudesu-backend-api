package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.AnimeSearchV2Response;

import java.io.IOException;
import java.util.List;

public interface AnimeSearchV2Service {

    List<AnimeSearchV2Response> get(String title) throws IOException;

}
