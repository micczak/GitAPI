package com.example.giftapi.controller;

import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.dto.KidDto;
import com.example.giftapi.service.KidService;
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
@RequestMapping("/api/v1/kids")
public class KidController {

    private final KidService kidService;

    @PostMapping
    public ResponseEntity<KidDto> save(@Valid @RequestBody CreateKidCommand createKidCommand) {
        return new ResponseEntity<>(kidService.save(createKidCommand), HttpStatus.CREATED);
    }
}