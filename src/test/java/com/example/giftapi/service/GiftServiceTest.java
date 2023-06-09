package com.example.giftapi.service;

import com.example.giftapi.model.Gift;
import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.repository.GiftRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GiftServiceTest {

    @InjectMocks
    private GiftService giftService;

    @Mock
    private GiftRepository giftRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveGift() {
        CreateGiftCommand command = new CreateGiftCommand();
        command.setName("Toy");
        command.setPrice(200.0);

        Gift gift = new Gift();
        gift.setId(1);
        gift.setName(command.getName());
        gift.setPrice(command.getPrice());

        when(giftRepository.save(any(Gift.class))).thenReturn(gift);

        assertEquals("Toy", giftService.save(command).getName());
        assertEquals(200.0, giftService.save(command).getPrice());
    }
}
