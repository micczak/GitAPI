package com.example.giftapi.service;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.command.UpdateKidCommand;
import com.example.giftapi.model.dto.KidDto;

import java.util.List;

public interface KidService {
    KidDto save(CreateKidCommand command);

    KidDto findById(int id);

    List<Kid> findAll();

    void deleteById(int kidId);

    KidDto update(int id, UpdateKidCommand command);
}
