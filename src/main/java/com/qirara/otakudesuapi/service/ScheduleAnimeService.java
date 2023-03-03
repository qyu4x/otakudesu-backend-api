package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.ScheduleAnimeResponse;

import java.io.IOException;
import java.util.List;

public interface ScheduleAnimeService {
    List<ScheduleAnimeResponse> getAll() throws IOException;
}
