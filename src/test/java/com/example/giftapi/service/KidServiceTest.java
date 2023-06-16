package com.example.giftapi.service;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.command.UpdateKidCommand;
import com.example.giftapi.model.dto.KidDto;
import com.example.giftapi.repository.KidRepository;
import com.example.giftapi.service.impl.KidServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class KidServiceTest {

    @Mock
    private KidRepository kidRepository;

    @InjectMocks
    private KidServiceImpl kidService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_ShouldSaveKidAndReturnDto() {
        // Arrange
        CreateKidCommand command = new CreateKidCommand();
        command.setBirthDate(LocalDate.of(2010, 1, 1));
        command.setName("John");
        command.setSurname("Doe");

        Kid savedKid = Kid.builder()
                .id(1)
                .birthDate(command.getBirthDate())
                .name(command.getName())
                .surname(command.getSurname())
                .build();

        when(kidRepository.save(any(Kid.class))).thenReturn(savedKid);

        // Act
        KidDto result = kidService.save(command);

        // Assert
        Assertions.assertEquals(savedKid.getId(), result.getId());
        Assertions.assertEquals(savedKid.getBirthDate(), result.getBirthDate());
        Assertions.assertEquals(savedKid.getName(), result.getName());
        Assertions.assertEquals(savedKid.getSurname(), result.getSurname());
        verify(kidRepository, times(1)).save(any(Kid.class));
    }

    @Test
    public void findById_WithExistingKidId_ShouldReturnKidDto() {
        // Arrange
        int kidId = 1;

        Kid kid = Kid.builder()
                .id(kidId)
                .birthDate(LocalDate.of(2010, 1, 1))
                .name("John")
                .surname("Doe")
                .build();

        when(kidRepository.findById(kidId)).thenReturn(Optional.of(kid));

        // Act
        KidDto result = kidService.findById(kidId);

        // Assert
        Assertions.assertEquals(kid.getId(), result.getId());
        Assertions.assertEquals(kid.getBirthDate(), result.getBirthDate());
        Assertions.assertEquals(kid.getName(), result.getName());
        Assertions.assertEquals(kid.getSurname(), result.getSurname());
    }

    @Test
    public void findById_WithNonExistingKidId_ShouldThrowException() {
        // Arrange
        int kidId = 1;

        when(kidRepository.findById(kidId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            kidService.findById(kidId);
        });
    }

    @Test
    public void findAll_ShouldReturnListOfKids() {
        // Arrange
        List<Kid> kids = Arrays.asList(
                Kid.builder().id(1).birthDate(LocalDate.of(2010, 1, 1)).name("John").surname("Doe").build(),
                Kid.builder().id(2).birthDate(LocalDate.of(2011, 2, 2)).name("Jane").surname("Smith").build()
        );

        when(kidRepository.findAll()).thenReturn(kids);

        // Act
        List<Kid> result = kidService.findAll();

        // Assert
        Assertions.assertEquals(kids.size(), result.size());
        Assertions.assertEquals(kids.get(0), result.get(0));
        Assertions.assertEquals(kids.get(1), result.get(1));
    }

    @Test
    public void deleteById_WithExistingKidId_ShouldDeleteKid() {
        // Arrange
        int kidId = 1;

        // Act
        kidService.deleteById(kidId);

        // Assert
        verify(kidRepository, times(1)).deleteById(kidId);
    }

    @Test
    public void deleteById_WithNonExistingKidId_ShouldThrowException() {
        // Arrange
        int kidId = 1;

        doThrow(EmptyResultDataAccessException.class).when(kidRepository).deleteById(kidId);

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            kidService.deleteById(kidId);
        });
    }

//    @Test
//    public void update_WithExistingKidId_ShouldUpdateKidAndReturnDto() {
//        // Arrange
//        int kidId = 1;
//        UpdateKidCommand command = new UpdateKidCommand();
//        command.setBirthDate(LocalDate.of(2012, 2, 2));
//        command.setName("Jane");
//        command.setSurname("Smith");
//
//        Kid existingKid = Kid.builder()
//                .id(kidId)
//                .birthDate(LocalDate.of(2010, 1, 1))
//                .name("John")
//                .surname("Doe")
//                .build();
//
//        when(kidRepository.findById(kidId)).thenReturn(Optional.of(existingKid));
//        when(kidRepository.save(any(Kid.class))).thenReturn(existingKid);
//
//        // Act
//        KidDto result = kidService.update(kidId, command);
//
//        // Assert
//        Assertions.assertEquals(existingKid.getId(), result.getId());
//        Assertions.assertEquals(command.getBirthDate(), result.getBirthDate());
//        Assertions.assertEquals(command.getName(), result.getName());
//        Assertions.assertEquals(command.getSurname(), result.getSurname());
//
//        ArgumentCaptor<Kid> captor = ArgumentCaptor.forClass(Kid.class);
//        verify(kidRepository, times(1)).save(captor.capture());
//        Kid updatedKid = captor.getValue();
//        Assertions.assertEquals(command.getBirthDate(), updatedKid.getBirthDate());
//        Assertions.assertEquals(command.getName(), updatedKid.getName());
//        Assertions.assertEquals(command.getSurname(), updatedKid.getSurname());
//    }

    @Test
    public void update_WithNonExistingKidId_ShouldThrowException() {
        // Arrange
        int kidId = 1;
        UpdateKidCommand command = new UpdateKidCommand();
        command.setBirthDate(LocalDate.of(2012, 2, 2));
        command.setName("Jane");
        command.setSurname("Smith");

        when(kidRepository.findById(kidId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            kidService.update(kidId, command);
        });
    }
}
