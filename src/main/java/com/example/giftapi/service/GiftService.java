package com.example.giftapi.service;

import com.example.giftapi.model.Gift;
import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;
import com.example.giftapi.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;

    public GiftDto save(int kidId, CreateGiftCommand command) {
        Gift toSave = command.toEntity();
        Gift saved = giftRepository.save(toSave);
        return GiftDto.fromEntity(saved);
    }

}