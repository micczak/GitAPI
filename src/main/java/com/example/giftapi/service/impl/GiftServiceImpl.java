package com.example.giftapi.service.impl;

import com.example.giftapi.model.Gift;
import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;
import com.example.giftapi.repository.GiftRepository;
import com.example.giftapi.service.GiftService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.giftapi.model.Kid;
import com.example.giftapi.repository.KidRepository;


import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;
    private final KidRepository kidRepository;

    @Override
    public GiftDto save(int kidId, CreateGiftCommand command) {
        Kid kid = kidRepository.findById(kidId).orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Kid with id={0} not found", kidId)));
        Gift toSave = command.toEntity();
        toSave.setKid(kid);
        Gift saved = giftRepository.save(toSave);
        return GiftDto.fromEntity(saved);
    }

    @Override
    public List<GiftDto> getGiftsForKid(int kidId) {
        return giftRepository.findByKidId(kidId).stream()
                .map(GiftDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public GiftDto getGiftForKid(int kidId, int giftId) {
        Gift gift = giftRepository.findByIdAndKidId(giftId, kidId)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Gift with id={0} not found for Kid with id={1}", giftId, kidId)));
        return GiftDto.fromEntity(gift);
    }

    @Override
    public GiftDto updateGiftForKid(int kidId, int giftId, CreateGiftCommand command) {
        Gift gift = giftRepository.findByIdAndKidId(giftId, kidId)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Gift with id={0} not found for Kid with id={1}", giftId, kidId)));
        gift.setName(command.getName());
        gift.setPrice(command.getPrice());
        Gift updated = giftRepository.save(gift);
        return GiftDto.fromEntity(updated);
    }

    @Override
    public void deleteGiftForKid(int kidId, int giftId) {
        giftRepository.deleteByIdAndKidId(giftId, kidId);
    }
}
