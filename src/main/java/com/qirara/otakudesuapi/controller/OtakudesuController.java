package com.qirara.otakudesuapi.controller;

import com.qirara.otakudesuapi.payload.response.AnimeListResponse;
import com.qirara.otakudesuapi.payload.response.LatestAnimeResponse;
import com.qirara.otakudesuapi.payload.response.WebResponse;
import com.qirara.otakudesuapi.service.AnimeListService;
import com.qirara.otakudesuapi.service.LatestAnimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/otakudesu/")
public class OtakudesuController {

    private final LatestAnimeService latestAnimeService;
    private final AnimeListService animeListService;

    public OtakudesuController(LatestAnimeService latestAnimeService, AnimeListService animeListService) {
        this.latestAnimeService = latestAnimeService;
        this.animeListService = animeListService;
    }

    @GetMapping("latest/{page}")
    public ResponseEntity<WebResponse<List<LatestAnimeResponse>>> listLatestAnime(@PathVariable Integer page) throws IOException {
        List<LatestAnimeResponse> latestAnimeResponses = latestAnimeService.getAll(page);
        WebResponse<List<LatestAnimeResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                latestAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("anime-list/{page}")
    public ResponseEntity<WebResponse<List<AnimeListResponse>>> listAnime(@PathVariable Integer page) throws IOException {
        List<AnimeListResponse> latestAnimeResponses = animeListService.getAll(page);
        WebResponse<List<AnimeListResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                latestAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

}
