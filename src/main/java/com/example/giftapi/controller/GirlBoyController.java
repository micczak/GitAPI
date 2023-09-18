package com.example.giftapi.controller;

import com.example.giftapi.model.command.CustomCreateKidCommand;
import com.example.giftapi.service.impl.GirlBoyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/girlboy")
public class GirlBoyController {

    private final GirlBoyService girlBoyService;

    @PostMapping
    public void create(@RequestBody CustomCreateKidCommand command) {
        girlBoyService.createKid(command);
    }
}
