package com.example.giftapi.mapper;

import com.example.giftapi.model.Gift;
import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;
import org.springframework.stereotype.Service;

@Service
public class GiftMapper {

    public GiftDto toDto(Gift gift) {
        return GiftDto.builder()
                .id(gift.getId())
                .name(gift.getName())
                .price(gift.getPrice())
                .build();
    }

    public Gift toEntity(CreateGiftCommand command) {
        return Gift.builder()
                .name(command.getName())
                .price(command.getPrice())
                .build();
    }

    public void update(CreateGiftCommand command, Gift gift) {
        gift.setName(command.getName());
        gift.setPrice(command.getPrice());
    }
}
