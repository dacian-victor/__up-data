package com.rss.feeder.feeder.web.api;

import com.rss.feeder.feeder.service.FeedItemDTO;
import com.rss.feeder.feeder.service.FeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FeederController {

    @Autowired
    private HttpServletRequest request;

    private FeederService feederService;

    public FeederController(FeederService feederService) {
        this.feederService = feederService;
    }

    @RequestMapping("/feed")
    @ResponseBody
    public List<FeedItemDTO> getFeed () {
        //feederService.makeTheRequest();
        System.out.println(request.getRemoteHost());

        return feederService.getFeed().stream().map(i->new FeedItemDTO(i)).collect(Collectors.toList());
    }

    @RequestMapping("/reload")
    @ResponseBody
    public List<FeedItemDTO> pushReload () {
        feederService.makeTheRequest();
        return feederService.getFeed().stream().map(i->new FeedItemDTO(i)).collect(Collectors.toList());
    }
}
