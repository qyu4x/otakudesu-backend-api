package com.qirara.otakudesuapi.controller;

import com.qirara.otakudesuapi.payload.response.CompleteAnimeV2Response;
import com.qirara.otakudesuapi.payload.response.OngoingAnimeV2Response;
import com.qirara.otakudesuapi.payload.response.ScheduleAnimeResponse;
import com.qirara.otakudesuapi.payload.response.WebResponse;
import com.qirara.otakudesuapi.service.CompleteAnimeV2Service;
import com.qirara.otakudesuapi.service.OngoingAnimeV2Service;
import com.qirara.otakudesuapi.service.ScheduleAnimeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/otakudesu/")
public class OtakudesuV2Controller {

    private final CompleteAnimeV2Service completeAnimeV2Service;

    private final OngoingAnimeV2Service ongoingAnimeV2Service;

    private final ScheduleAnimeService scheduleAnimeService;

    @Autowired
    public OtakudesuV2Controller(CompleteAnimeV2Service completeAnimeV2Service, OngoingAnimeV2Service ongoingAnimeV2Service, ScheduleAnimeService scheduleAnimeService) {
        this.completeAnimeV2Service = completeAnimeV2Service;
        this.ongoingAnimeV2Service = ongoingAnimeV2Service;
        this.scheduleAnimeService = scheduleAnimeService;
    }


    @Operation(summary = "get list of the complete anime")
    @GetMapping("complete-anime/{page}")
    public ResponseEntity<WebResponse<List<CompleteAnimeV2Response>>> listCompletenime(@PathVariable Integer page) throws IOException {
        List<CompleteAnimeV2Response> completeAnimeV2Responses = completeAnimeV2Service.getAll(page);
        WebResponse<List<CompleteAnimeV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                completeAnimeV2Responses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @Operation(summary = "get list of the ongoing anime")
    @GetMapping("ongoing-anime/{page}")
    public ResponseEntity<WebResponse<List<OngoingAnimeV2Response>>> listOngoingAnime(@PathVariable Integer page) throws IOException {
        List<OngoingAnimeV2Response> ongoingAnimeV2Responses = ongoingAnimeV2Service.getAll(page);
        WebResponse<List<OngoingAnimeV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ongoingAnimeV2Responses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @Operation(summary = "get list of the schedule anime ongoing broadcast day")
    @GetMapping("schedule")
    public ResponseEntity<WebResponse<List<ScheduleAnimeResponse>>> scheduleAnime() throws IOException {
        List<ScheduleAnimeResponse> scheduleAnimeResponses = scheduleAnimeService.getAll();
        WebResponse<List<ScheduleAnimeResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                scheduleAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }
}
