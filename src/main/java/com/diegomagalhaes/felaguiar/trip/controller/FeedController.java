package com.diegomagalhaes.felaguiar.trip.controller;

import com.diegomagalhaes.felaguiar.trip.instagram.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.instagram.api.PagedMediaList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class FeedController {
    @Autowired
    private FeedService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index(Map<String, Object> model){
        PagedMediaList mediaList = service.getMediasByTag();

        model.put("mediaList", mediaList.getList());
        model.put("pagination", mediaList.getPagination());
        return "home";
    }
}
