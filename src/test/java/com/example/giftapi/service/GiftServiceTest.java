package com.example.giftapi.service;
import com.example.giftapi.model.Gift;
import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateGiftCommand;
import com.example.giftapi.model.dto.GiftDto;
import com.example.giftapi.repository.GiftRepository;
import com.example.giftapi.repository.KidRepository;
import com.example.giftapi.service.GiftService;
import com.example.giftapi.service.impl.GiftServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class GiftServiceTest {

    @Mock
    private GiftRepository giftRepository;

    @Mock
    private KidRepository kidRepository;

    @InjectMocks
    private GiftServiceImpl giftService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_ShouldSaveGiftAndReturnDto() {
        // Arrange
        int kidId = 1;
        CreateGiftCommand command = new CreateGiftCommand();
        command.setName("Toy");
        command.setPrice(10.0);

        Gift savedGift = Gift.builder()
                .id(1)
                .name(command.getName())
                .price(command.getPrice())
                .build();

        when(kidRepository.findById(kidId)).thenReturn(Optional.of(new Kid()));
        when(giftRepository.save(any(Gift.class))).thenReturn(savedGift);

        // Act
        GiftDto result = giftService.save(kidId, command);

        // Assert
        Assertions.assertEquals(savedGift.getId(), result.getId());
        Assertions.assertEquals(savedGift.getName(), result.getName());
        Assertions.assertEquals(savedGift.getPrice(), result.getPrice());
        verify(giftRepository, times(1)).save(any(Gift.class));
    }

    @Test
    public void getGiftsForKid_ShouldReturnListOfGifts() {
        // Arrange
        int kidId = 1;

        List<Gift> gifts = Arrays.asList(
                Gift.builder().id(1).name("Toy").price(10.0).build(),
                Gift.builder().id(2).name("Book").price(15.0).build()
        );

        when(giftRepository.findByKidId(kidId)).thenReturn(gifts);

        // Act
        List<GiftDto> result = giftService.getGiftsForKid(kidId);

        // Assert
        Assertions.assertEquals(gifts.size(), result.size());
        for (int i = 0; i < gifts.size(); i++) {
            Gift expectedGift = gifts.get(i);
            GiftDto actualGiftDto = result.get(i);
            Assertions.assertEquals(expectedGift.getId(), actualGiftDto.getId());
            Assertions.assertEquals(expectedGift.getName(), actualGiftDto.getName());
            Assertions.assertEquals(expectedGift.getPrice(), actualGiftDto.getPrice());
        }
    }

    @Test
    public void getGiftForKid_WithExistingGiftId_ShouldReturnGiftDto() {
        // Arrange
        int kidId = 1;
        int giftId = 1;

        Gift gift = Gift.builder().id(giftId).name("Toy").price(10.0).build();

        when(giftRepository.findByIdAndKidId(giftId, kidId)).thenReturn(Optional.of(gift));

        // Act
        GiftDto result = giftService.getGiftForKid(kidId, giftId);

        // Assert
        Assertions.assertEquals(gift.getId(), result.getId());
        Assertions.assertEquals(gift.getName(), result.getName());
        Assertions.assertEquals(gift.getPrice(), result.getPrice());
    }

    @Test
    public void getGiftForKid_WithNonExistingGiftId_ShouldThrowException() {
        // Arrange
        int kidId = 1;
        int giftId = 1;

        when(giftRepository.findByIdAndKidId(giftId, kidId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            giftService.getGiftForKid(kidId, giftId);
        });
    }

    @Test
    public void updateGiftForKid_WithExistingGiftId_ShouldUpdateGiftAndReturnDto() {
        // Arrange
        int kidId = 1;
        int giftId = 1;
        CreateGiftCommand command = new CreateGiftCommand();
        command.setName("New Toy");
        command.setPrice(20.0);

        Gift existingGift = Gift.builder().id(giftId).name("Toy").price(10.0).build();

        when(giftRepository.findByIdAndKidId(giftId, kidId)).thenReturn(Optional.of(existingGift));
        when(giftRepository.save(any(Gift.class))).thenReturn(existingGift);

        // Act
        GiftDto result = giftService.updateGiftForKid(kidId, giftId, command);

        // Assert
        Assertions.assertEquals(existingGift.getId(), result.getId());
        Assertions.assertEquals(command.getName(), result.getName());
        Assertions.assertEquals(command.getPrice(), result.getPrice());

        ArgumentCaptor<Gift> captor = ArgumentCaptor.forClass(Gift.class);
        verify(giftRepository, times(1)).save(captor.capture());
        Gift updatedGift = captor.getValue();
        Assertions.assertEquals(command.getName(), updatedGift.getName());
        Assertions.assertEquals(command.getPrice(), updatedGift.getPrice());
    }

    @Test
    public void updateGiftForKid_WithNonExistingGiftId_ShouldThrowException() {
        // Arrange
        int kidId = 1;
        int giftId = 1;
        CreateGiftCommand command = new CreateGiftCommand();
        command.setName("New Toy");
        command.setPrice(20.0);

        when(giftRepository.findByIdAndKidId(giftId, kidId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            giftService.updateGiftForKid(kidId, giftId, command);
        });
    }

    @Test
    public void deleteGiftForKid_WithExistingGiftId_ShouldDeleteGift() {
        // Arrange
        int kidId = 1;
        int giftId = 1;

        // Act
        giftService.deleteGiftForKid(kidId, giftId);

        // Assert
        verify(giftRepository, times(1)).deleteByIdAndKidId(giftId, kidId);
    }
}
