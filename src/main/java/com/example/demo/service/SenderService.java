package com.example.demo.service;

import com.example.demo.model.SendModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Service
public class SenderService {

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public Mono<String> sendMessage(SendModel sendModel) {
        return Mono.fromSupplier(() -> {
            Stream.iterate(1, n -> n + 1).limit(10).forEach(idx -> {
                try {
                    sendModel.setCount(idx);
                    rabbitMessagingTemplate.convertAndSend("TestQueue", objectMapper.writeValueAsString(sendModel));
                    rabbitMessagingTemplate.convertAndSend("Test2Queue", objectMapper.writeValueAsString(sendModel));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            return "SUCCESS";
        });
    }
}
