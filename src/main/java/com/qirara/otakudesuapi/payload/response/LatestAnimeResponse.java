package com.qirara.otakudesuapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LatestAnimeResponse {

    private String imageURL;

    private String title;

    private String linkHref;

}
