package com.example.giftapi.model.command;


import com.example.giftapi.model.Kid;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class CreateKidCommand {

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    public Kid toEntity(){
//        return Kid.builder()
//                .birthDate(birthDate)
//                .name(name)
//                .surname(surname)
//                .build();
        return null;
    }
}
