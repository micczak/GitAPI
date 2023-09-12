package com.example.giftapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SuperBuilder
@Entity
public abstract class Kid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String name;

    private String surname;

}
