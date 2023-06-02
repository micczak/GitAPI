package com.example.giftapi.model.command;


import com.example.giftapi.model.Kid;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateKidCommand {

    private LocalDate birthDate;
    private String name;
    private String surname;

    public Kid toEntity(){
        return Kid.builder()
                .birthDate(birthDate)
                .name(name)
                .surname(surname)
                .build();
    }
//TODO present (nazwa, cena).
    //TODO na siłach: walidacje itp itd
}
