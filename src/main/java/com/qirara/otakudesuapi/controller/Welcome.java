package com.qirara.otakudesuapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class Welcome {

    public String welcome() {
        return "Hello welcome please use this api wisely";
    }
}
