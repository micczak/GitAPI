package com.example.giftapi.controller;


import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;
import com.example.giftapi.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifts")
public class GiftController {

    private final GiftService giftService;

    @PostMapping
    public ResponseEntity<GiftDto> save(@Valid @RequestBody CreateGiftCommand createGiftCommand) {
        return new ResponseEntity<>(giftService.save(createGiftCommand), HttpStatus.CREATED);
    }
}
