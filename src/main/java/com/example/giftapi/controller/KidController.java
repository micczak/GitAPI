package com.example.giftapi.controller;

import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.command.UpdateKidCommand;
import com.example.giftapi.model.dto.GiftDto;
import com.example.giftapi.model.dto.KidDto;
import com.example.giftapi.service.GiftService;
import com.example.giftapi.service.KidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kids")
public class KidController {

    private final KidService kidService;
    private final GiftService giftService;

    @PostMapping
    public ResponseEntity<KidDto> save(@Valid @RequestBody CreateKidCommand createKidCommand) {
        return new ResponseEntity<>(kidService.save(createKidCommand), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public KidDto findById(@PathVariable int id) {
        return kidService.findById(id);
    }

    @GetMapping
    public List<KidDto> findAll(){
        return kidService.findAll().stream()
                .map(KidDto::fromEntity)
                .toList();
    }

    @PutMapping("/{id}")
    public KidDto update(@PathVariable int id, @RequestBody UpdateKidCommand command){
        return kidService.update(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        kidService.deleteById(id);
    }

    @PostMapping("/{kidId}/gifts")
    public GiftDto saveGift(@PathVariable int kidId, @RequestBody CreateGiftCommand command) {
        return giftService.save(kidId, command);
    }


}