package com.example.giftapi.model.command;

import com.example.giftapi.model.Kid;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateKidCommand {

    private LocalDate birthDate;
    private String name;
    private String surname;


}
