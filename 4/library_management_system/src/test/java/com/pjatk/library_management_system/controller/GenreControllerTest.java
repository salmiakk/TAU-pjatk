package com.pjatk.library_management_system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjatk.library_management_system.model.Author;
import com.pjatk.library_management_system.model.Genre;
import com.pjatk.library_management_system.repository.AuthorRepository;
import com.pjatk.library_management_system.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreRepository genreRepository;

    String genreJson = null;
    Genre genre1 = null;

    @BeforeEach
    void setup() throws JsonProcessingException {
        genre1 = new Genre("obyczajowa");
        ObjectMapper mapper = new ObjectMapper();
        genreJson = mapper.writeValueAsString(genre1);
    }
    @Test
    void findAllGenres() throws Exception {
        when(genreRepository.findAll()).thenReturn(List.of(genre1));
        mockMvc.perform(get("/genre"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveGenre() throws Exception {
        mockMvc.perform(post("/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(genreJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}