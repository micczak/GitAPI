package com.example.giftapi.model.command;

import com.example.giftapi.model.Gift;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateGiftCommand {

    @NotEmpty
    private String name;

    @NotNull
    @Positive
    private double price;

    public Gift toEntity(){
        return Gift.builder()
                .name(name)
                .price(price)
                .build();
    }
}
