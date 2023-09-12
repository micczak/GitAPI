package com.example.giftapi.service.impl;

import com.example.giftapi.model.Boy;
import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CustomCreateKidCommand;
import com.example.giftapi.service.KidCreationStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component("BOY")
public class BoyCreationStrategy implements KidCreationStrategy {
    @Override
    public Boy create(CustomCreateKidCommand command) {
        Map<String, String> params = command.getParams();
        return Boy.builder()
                .name(params.get("firstName"))
                .surname(params.get("lastName"))
                .birthDate(LocalDate.parse(params.get("birthDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .penisLength(Double.parseDouble(params.get("penisLength")))
                .build();
    }
}
