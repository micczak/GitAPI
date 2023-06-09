package com.example.giftapi.service;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.repository.KidRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class KidServiceTest {

    @InjectMocks
    private KidService kidService;

    @Mock
    private KidRepository kidRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveKid() {
        CreateKidCommand command = new CreateKidCommand();
        command.setBirthDate(LocalDate.now());
        command.setName("Tom");
        command.setSurname("Smith");

        Kid kid = new Kid();
        kid.setId(1);
        kid.setBirthDate(command.getBirthDate());
        kid.setName(command.getName());
        kid.setSurname(command.getSurname());

        when(kidRepository.save(any(Kid.class))).thenReturn(kid);

        assertEquals("Tom", kidService.save(command).getName());
        assertEquals("Smith", kidService.save(command).getSurname());
    }
}
