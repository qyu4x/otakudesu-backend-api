package com.qirara.otakudesuapi.service;

import com.qirara.otakudesuapi.payload.response.CompleteAnimeV2Response;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.io.IOException;
import java.util.List;

public interface CompleteAnimeV2Service {

    List<CompleteAnimeV2Response> getAll(Integer page) throws IOException;

}
