package com.qirara.otakudesuapi.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleAnimeResponse {

    private String broadcastDay;

    private List<ScheduleAnimeDetailResponse> anime;

}
