package com.example.giftapi.model.dto;

import com.example.giftapi.model.Gift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftDto {
    private int id;
    private String name;
    private double price;

    public static GiftDto fromEntity(Gift gift){
        return GiftDto.builder()
                .id(gift.getId())
                .name(gift.getName())
                .price(gift.getPrice())
                .build();
    }
}