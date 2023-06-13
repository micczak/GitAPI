//package com.example.giftapi.service;
//
//import com.example.giftapi.model.command.CreateGiftCommand;
//import com.example.giftapi.model.Gift;
//import com.example.giftapi.model.dto.GiftDto;
//import com.example.giftapi.repository.GiftRepository;
//import com.example.giftapi.service.GiftService;
//import com.example.giftapi.service.impl.GiftServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class GiftServiceTest {
//
//    @InjectMocks
//    private GiftServiceImpl giftService;
//
//    @Mock
//    private GiftRepository giftRepository;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void shouldSaveGift() {
//        CreateGiftCommand command = new CreateGiftCommand();
//        command.setName("Toy");
//        command.setPrice(200.0);
//
//        Gift gift = new Gift();
//        gift.setId(1);
//        gift.setName(command.getName());
//        gift.setPrice(command.getPrice());
//
//        when(giftRepository.save(any(Gift.class))).thenReturn(gift);
//
//        GiftDto savedGift = giftService.save(1, command);
//
//        assertEquals("Toy", savedGift.getName());
//        assertEquals(200.0, savedGift.getPrice());
//    }
//}
