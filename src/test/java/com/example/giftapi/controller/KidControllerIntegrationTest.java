package com.example.giftapi.controller;

import com.example.giftapi.model.Kid;
import com.example.giftapi.model.command.CreateKidCommand;
import com.example.giftapi.model.command.UpdateKidCommand;
import com.example.giftapi.model.dto.KidDto;
import com.example.giftapi.service.KidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class KidControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KidService kidService;

    @Test
    public void saveKid_ShouldReturnCreatedKid() throws Exception {
        CreateKidCommand createKidCommand = new CreateKidCommand();
        createKidCommand.setBirthDate(LocalDate.of(2005, 6, 1));
        createKidCommand.setName("John");
        createKidCommand.setSurname("Doe");

        KidDto expectedKidDto = KidDto.builder()
                .id(1)
                .birthDate(createKidCommand.getBirthDate())
                .name(createKidCommand.getName())
                .surname(createKidCommand.getSurname())
                .build();

        when(kidService.save(Mockito.any(CreateKidCommand.class))).thenReturn(expectedKidDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/kids")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createKidCommand)))
                .andReturn();

        assertEquals(201, result.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(expectedKidDto), result.getResponse().getContentAsString());
    }

    @Test
    public void findById_WithValidId_ShouldReturnKid() throws Exception {
        int kidId = 1;

        KidDto expectedKidDto = KidDto.builder()
                .id(kidId)
                .birthDate(LocalDate.of(2005, 6, 1))
                .name("John")
                .surname("Doe")
                .build();

        when(kidService.findById(kidId)).thenReturn(expectedKidDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/kids/{id}", kidId))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(expectedKidDto), result.getResponse().getContentAsString());
    }

    @Test
    public void findById_WithInvalidId_ShouldReturnNotFound() throws Exception {
        int kidId = 1;

        when(kidService.findById(kidId)).thenReturn(null);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/kids/{id}", kidId))
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    public void findAll_ShouldReturnListOfKids() throws Exception {
        List<Kid> expectedKids = Arrays.asList(
                Kid.builder()
                        .id(1)
                        .birthDate(LocalDate.of(2005, 6, 1))
                        .name("John")
                        .surname("Doe")
                        .build(),
                Kid.builder()
                        .id(2)
                        .birthDate(LocalDate.of(2007, 3, 15))
                        .name("Jane")
                        .surname("Smith")
                        .build()
        );

        List<KidDto> expectedKidDtos = expectedKids.stream()
                .map(KidDto::fromEntity)
                .collect(Collectors.toList());

        when(kidService.findAll()).thenReturn(expectedKids);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/kids"))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(expectedKidDtos), result.getResponse().getContentAsString());
    }


    @Test
    public void deleteById_WithValidId_ShouldReturnNoContent() throws Exception {
        int kidId = 1;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/kids/{id}", kidId))
                .andReturn();

        assertEquals(204, result.getResponse().getStatus());
    }

    @Test
    public void update_WithValidId_ShouldReturnUpdatedKid() throws Exception {
        int kidId = 1;

        UpdateKidCommand updateKidCommand = new UpdateKidCommand();
        updateKidCommand.setBirthDate(LocalDate.of(2005, 6, 1));
        updateKidCommand.setName("John");
        updateKidCommand.setSurname("Doe");

        KidDto expectedKidDto = KidDto.builder()
                .id(kidId)
                .birthDate(updateKidCommand.getBirthDate())
                .name(updateKidCommand.getName())
                .surname(updateKidCommand.getSurname())
                .build();

        when(kidService.update(Mockito.eq(kidId), Mockito.any(UpdateKidCommand.class))).thenReturn(expectedKidDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/kids/{id}", kidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateKidCommand)))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(expectedKidDto), result.getResponse().getContentAsString());
    }

    @Test
    public void update_WithInvalidId_ShouldReturnNotFound() throws Exception {
        int kidId = 1;

        UpdateKidCommand updateKidCommand = new UpdateKidCommand();
        updateKidCommand.setBirthDate(LocalDate.of(2005, 6, 1));
        updateKidCommand.setName("John");
        updateKidCommand.setSurname("Doe");

        when(kidService.update(Mockito.eq(kidId), Mockito.any(UpdateKidCommand.class))).thenReturn(null);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/kids/{id}", kidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateKidCommand)))
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }
}
