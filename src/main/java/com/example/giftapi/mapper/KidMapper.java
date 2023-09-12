package com.example.giftapi.mapper;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.command.UpdateKidCommand;
import com.example.giftapi.model.dto.KidDto;
import org.springframework.stereotype.Service;

//można nie budowac jako serwis (bez wstrzykiwania do uzycia, metody statyczne).
@Service
public class KidMapper {

    public Kid fromDto(CreateKidCommand command){
//        return Kid.builder()
//                .name(command.getName())
//                .surname(command.getSurname())
//                .birthDate(command.getBirthDate())
//                .build();
        return null; //kurczak approved
    }

    public KidDto toDto(Kid kid){
        return KidDto.builder()
                .name(kid.getName())
                .surname(kid.getSurname())
                .birthDate(kid.getBirthDate())
                .build();
    }

    public void update(UpdateKidCommand source, Kid target){
        target.setBirthDate(source.getBirthDate());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
    }

}
