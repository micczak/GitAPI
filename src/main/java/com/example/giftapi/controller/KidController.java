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
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kids")
public class KidController {

    private final KidService kidService;
    private final GiftService giftService;

    @PostMapping
    public ResponseEntity<KidDto> save(@Valid @RequestBody CreateKidCommand createKidCommand) {
        KidDto createdKid = kidService.save(createKidCommand);
        return ResponseEntity.created(URI.create("/api/v1/kids/" + createdKid.getId())).body(createdKid);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KidDto> findById(@PathVariable int id) {
        KidDto kidDto = kidService.findById(id);
        if (kidDto != null) {
            return ResponseEntity.ok(kidDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<KidDto>> findAll(){
        List<KidDto> kids = kidService.findAll().stream()
                .map(KidDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kids);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KidDto> update(@PathVariable int id, @RequestBody UpdateKidCommand command){
        KidDto updatedKidDto = kidService.update(id, command);
        if (updatedKidDto != null) {
            return ResponseEntity.ok(updatedKidDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        kidService.deleteById(id);
    }

    @PostMapping("/{kidId}/gifts")
    public ResponseEntity<GiftDto> saveGift(@PathVariable int kidId, @RequestBody CreateGiftCommand command) {
        GiftDto createdGift = giftService.save(kidId, command);
        return ResponseEntity.created(URI.create("/api/v1/kids/" + kidId + "/gifts/" + createdGift.getId())).body(createdGift);
    }

    @GetMapping("/{kidId}/gifts")
    public ResponseEntity<List<GiftDto>> getGifts(@PathVariable int kidId) {
        List<GiftDto> gifts = giftService.getGiftsForKid(kidId);
        return ResponseEntity.ok(gifts);
    }

    @GetMapping("/{kidId}/gifts/{giftId}")
    public ResponseEntity<GiftDto> getGift(@PathVariable int kidId, @PathVariable int giftId) {
        return ResponseEntity.ok(giftService.getGiftForKid(kidId, giftId));
    }

    @PutMapping("/{kidId}/gifts/{giftId}")
    public ResponseEntity<GiftDto> updateGift(@PathVariable int kidId, @PathVariable int giftId, @RequestBody CreateGiftCommand command) {
        return ResponseEntity.ok(giftService.updateGiftForKid(kidId, giftId, command));
    }

    @DeleteMapping("/{kidId}/gifts/{giftId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGift(@PathVariable int kidId, @PathVariable int giftId) {
        giftService.deleteGiftForKid(kidId, giftId);
    }
}
