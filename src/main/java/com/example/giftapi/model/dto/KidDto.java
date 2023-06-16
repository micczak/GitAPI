package com.example.giftapi.model.dto;

import com.example.giftapi.model.Kid;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KidDto {
    private int id;
    private LocalDate birthDate;
    private String name;
    private String surname;

    public static KidDto fromEntity(Kid kid){
        return KidDto.builder()
                .id(kid.getId())
                .birthDate(kid.getBirthDate())
                .name(kid.getName())
                .surname(kid.getSurname())
                .build();
    }
}
