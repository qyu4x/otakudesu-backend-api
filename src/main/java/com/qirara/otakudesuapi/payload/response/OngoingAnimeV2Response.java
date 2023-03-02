package com.qirara.otakudesuapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OngoingAnimeV2Response {

    private String title;

    private String currentEpisode;

    private String releaseDay;

    private String lastUpdate;

    private String imageURL;

    private String linkHref;

}
