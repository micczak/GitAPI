package com.example.giftapi.service.impl;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CustomCreateKidCommand;
import com.example.giftapi.repository.KidRepository;
import com.example.giftapi.service.KidCreationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GirlBoyService {

    private  final KidRepository kidRepository;
    private final Map<String, KidCreationStrategy> kidCreationStrategies;

    public void createKid(CustomCreateKidCommand command){
        KidCreationStrategy creationStrategy = kidCreationStrategies.get(command.getType());
        Kid kid = creationStrategy.create(command);
        System.out.println(kid);
    }
}
