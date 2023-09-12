package com.example.giftapi.service;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CustomCreateKidCommand;

public interface KidCreationStrategy {

    Kid create(CustomCreateKidCommand command);
}
