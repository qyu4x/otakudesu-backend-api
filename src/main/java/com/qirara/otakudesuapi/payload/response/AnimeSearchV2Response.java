package com.qirara.otakudesuapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimeSearchV2Response {

    private String title;

    private String imageURL;

    private String genre;

    private String status;

    private String rating;

    private String linkHref;

}
