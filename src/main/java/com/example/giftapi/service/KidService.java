package com.example.giftapi.service;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.dto.KidDto;
import com.example.giftapi.repository.KidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KidService {

    private final KidRepository kidRepository;

    public KidDto save(CreateKidCommand command) {
        Kid toSave = command.toEntity();
        Kid saved = kidRepository.save(toSave);
        return KidDto.fromEntity(saved);
    }

}
