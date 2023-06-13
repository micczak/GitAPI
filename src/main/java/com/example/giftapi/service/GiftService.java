package com.example.giftapi.service;

import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;

import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;

import java.util.List;

public interface GiftService {
    GiftDto save(int kidId, CreateGiftCommand command);

    List<GiftDto> getGiftsForKid(int kidId);

    GiftDto getGiftForKid(int kidId, int giftId);

    GiftDto updateGiftForKid(int kidId, int giftId, CreateGiftCommand command);

    void deleteGiftForKid(int kidId, int giftId);
}
