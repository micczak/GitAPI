//package com.example.giftapi.controller;
//
//
//import com.example.giftapi.model.command.CreateKidCommand;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.example.giftapi.model.command.CreateGiftCommand;
//import com.example.giftapi.model.command.CreateKidCommand;
//import com.example.giftapi.model.command.UpdateKidCommand;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class KidControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void shouldCreateKid() throws Exception {
//        CreateKidCommand command = new CreateKidCommand();
//        command.setName("John");
//        command.setSurname("Doe");
//        command.setBirthDate(LocalDate.of(2010, 1, 1));
//
//        mockMvc.perform(post("/api/v1/kids")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(command)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void shouldFindKid() throws Exception {
//        mockMvc.perform(get("/api/v1/kids/{id}", 1))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldFindAllKids() throws Exception {
//        mockMvc.perform(get("/api/v1/kids"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldUpdateKid() throws Exception {
//        UpdateKidCommand command = new UpdateKidCommand();
//        command.setName("John");
//        command.setSurname("Doe");
//
//        mockMvc.perform(put("/api/v1/kids/{id}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(command)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldDeleteKid() throws Exception {
//        mockMvc.perform(delete("/api/v1/kids/{id}", 1))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void shouldCreateGiftForKid() throws Exception {
//        CreateGiftCommand command = new CreateGiftCommand();
//        command.setName("Toy");
//        command.setPrice(100.0);
//
//        mockMvc.perform(post("/api/v1/kids/{kidId}/gifts", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(command)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void shouldGetGiftsForKid() throws Exception {
//        mockMvc.perform(get("/api/v1/kids/{kidId}/gifts", 1))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldGetGiftForKid() throws Exception {
//        mockMvc.perform(get("/api/v1/kids/{kidId}/gifts/{giftId}", 1, 1))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldUpdateGiftForKid() throws Exception {
//        CreateGiftCommand command = new CreateGiftCommand();
//        command.setName("Updated Toy");
//        command.setPrice(150.0);
//
//        mockMvc.perform(put("/api/v1/kids/{kidId}/gifts/{giftId}", 1, 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(command)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldDeleteGiftForKid() throws Exception {
//        mockMvc.perform(delete("/api/v1/kids/{kidId}/gifts/{giftId}", 1, 1))
//                .andExpect(status().isNoContent());
//    }
//}
