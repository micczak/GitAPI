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
public class Girl extends Kid {

    private double weight;
}
