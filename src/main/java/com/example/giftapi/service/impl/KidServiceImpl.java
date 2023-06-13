package com.example.giftapi.service.impl;

import com.example.giftapi.mapper.KidMapper;
import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.command.UpdateKidCommand;
import com.example.giftapi.model.dto.KidDto;
import com.example.giftapi.repository.KidRepository;
import com.example.giftapi.service.KidService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KidServiceImpl implements KidService {

    private final KidRepository kidRepository;
    private final KidMapper kidMapper;

    @Override
    public KidDto save(CreateKidCommand command) {
        Kid toSave = command.toEntity();
        Kid saved = kidRepository.save(toSave);
        return KidDto.fromEntity(saved);
    }

    @Override
    public KidDto findById(int id) {
        Kid kid = kidRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Kid with id={0} not found", id)));
        return KidDto.fromEntity(kid);
    }

    @Override
    public List<Kid> findAll(){
        return kidRepository.findAll();
    }

    @Override
    public void deleteById(int kidId){
        kidRepository.deleteById(kidId);
    }

    @Override
    public KidDto update(int id, UpdateKidCommand command){
        Kid kidToUpdate = kidRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Kid with id={0} not found", id)));
        kidMapper.update(command, kidToUpdate);
        return kidMapper.toDto(kidRepository.save(kidToUpdate));
    }
    //TODO ZROBIĆ MAPER GIFTÓW
    //TODO walidacja, logika, testy, idiotoodporność taka sytuacja


}
