package com.example.giftapi.model.command;

import lombok.Data;

import java.util.Map;

@Data
public class CustomCreateKidCommand {

    private String type;
    private Map<String, String> params;
}
