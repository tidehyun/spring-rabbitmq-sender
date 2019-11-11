package com.example.demo.controller;

import com.example.demo.model.SendModel;
import com.example.demo.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SenderController {

    @Autowired
    private SenderService senderService;

    @PostMapping(value = "/send")
    public Mono<String> sendMeesage(@RequestBody SendModel sendModel) {
        return senderService.sendMessage(sendModel);
    }
}
