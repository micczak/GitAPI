package com.example.giftapi.model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@ToString(callSuper = true)
public class Boy extends Kid {

    private double penisLength;
}
