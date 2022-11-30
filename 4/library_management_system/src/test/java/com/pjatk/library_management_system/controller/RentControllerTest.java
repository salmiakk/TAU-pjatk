package com.pjatk.library_management_system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjatk.library_management_system.model.*;
import com.pjatk.library_management_system.repository.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class RentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentRepository rentRepository;

    String rentJson = null;
    Rent rent1 = null;
    Reader reader1 = null;
    Book book1 = null;
    Genre genre1 = null;
    Author author1 = null;

    @BeforeEach
    void setup() throws JsonProcessingException {
        reader1 = new Reader(1L, "Grzegorz","Brzeczyszczykiewicz", 98205829384L, 739582058L, "grzegorz@wp.pl");
        genre1 = new Genre("obyczajowa");
        author1 = new Author("Mateusz","Pioch");
        book1 = new Book(1L,"W krainie deszcowcow", 12345L, "2019", "Polish", genre1, author1);
        rent1 = new Rent(1L,book1, reader1);
        ObjectMapper mapper = new ObjectMapper();
        rentJson = mapper.writeValueAsString(rent1);
    }
    @Test
    void findAll() throws Exception {
        when(rentRepository.findAll()).thenReturn(List.of(rent1));

        mockMvc.perform(get("/rent"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void saveRent() throws Exception {
        mockMvc.perform(post("/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(rentJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}