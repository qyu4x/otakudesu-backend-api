package com.qirara.otakudesuapi.controller;

import com.qirara.otakudesuapi.payload.response.*;
import com.qirara.otakudesuapi.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/otakudesu/")
public class OtakudesuController {

    private final LatestAnimeService latestAnimeService;

    private final AnimeListService animeListService;

    private final OngoingAnimeService ongoingAnimeService;

    private final AnimeMoviesService animeMoviesService;

    private final AnimeGenresService animeGenresService;


    private final AnimeDetailService animeDetailService;

    private final AnimeDetailEpisodeService animeDetailEpisodeService;

    private final AnimeSearchService animeSearchService;



    public OtakudesuController(LatestAnimeService latestAnimeService, AnimeListService animeListService, OngoingAnimeService ongoingAnimeService, AnimeMoviesService animeMoviesService, AnimeGenresService animeGenresService, AnimeDetailService animeDetailService, AnimeDetailEpisodeService animeDetailEpisodeService, AnimeSearchService animeSearchService) {
        this.latestAnimeService = latestAnimeService;
        this.animeListService = animeListService;
        this.ongoingAnimeService = ongoingAnimeService;
        this.animeMoviesService = animeMoviesService;
        this.animeGenresService = animeGenresService;
        this.animeDetailService = animeDetailService;
        this.animeDetailEpisodeService = animeDetailEpisodeService;
        this.animeSearchService = animeSearchService;
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

    @GetMapping("ongoing-list/{page}")
    public ResponseEntity<WebResponse<List<OngoingAnimeResponse>>> ongoingAnime(@PathVariable Integer page) throws IOException {
        List<OngoingAnimeResponse> ongoingAnimeResponses = ongoingAnimeService.getAll(page);
        WebResponse<List<OngoingAnimeResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ongoingAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("movie-list/{page}")
    public ResponseEntity<WebResponse<List<AnimeMoviesResponse>>> animeMovie(@PathVariable Integer page) throws IOException {
        List<AnimeMoviesResponse> moviesResponses = animeMoviesService.getAll(page);
        WebResponse<List<AnimeMoviesResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                moviesResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("genres")
    public ResponseEntity<WebResponse<List<AnimeGenreResponse>>> animeGenres() throws IOException {
        List<AnimeGenreResponse> animeGenreResponses = animeGenresService.getAll();
        WebResponse<List<AnimeGenreResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                animeGenreResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("genre/{genre}/{page}")
    public ResponseEntity<WebResponse<List<AnimeListResponse>>> animeGenreDetails(@PathVariable String genre, @PathVariable Integer page) throws IOException {
        List<AnimeListResponse> animeGenreResponses = animeGenresService.get(genre, page);
        WebResponse<List<AnimeListResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                animeGenreResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("detail/{href}")
    public ResponseEntity<WebResponse<AnimeDetailResponse>> animeDetails(@PathVariable String href) throws IOException {
        AnimeDetailResponse animeDetailResponse = animeDetailService.getDetails(href);
        WebResponse<AnimeDetailResponse> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                animeDetailResponse
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("/{href}")
    public ResponseEntity<WebResponse<AnimeDetailEpisodeResponse>> animeEpisodeDetails(@PathVariable String href, @RequestParam Integer ep) throws IOException {
        AnimeDetailEpisodeResponse animeDetailEpisodeResponse = animeDetailEpisodeService.get(href, ep);
        WebResponse<AnimeDetailEpisodeResponse> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                animeDetailEpisodeResponse
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @GetMapping("find")
    public ResponseEntity<WebResponse<List<AnimeListResponse>>> animeSearch(@RequestParam String keyword) throws IOException {
        List<AnimeListResponse> animeSearchResponses = animeSearchService.get(keyword);
        WebResponse<List<AnimeListResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                animeSearchResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

}
