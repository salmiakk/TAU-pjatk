package com.pjatk.library_management_system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjatk.library_management_system.model.Author;
import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Genre;
import com.pjatk.library_management_system.repository.AuthorRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    String authorJson = null;
    Author author1 = null;
    Author author2 = null;
    Genre genre1 = null;
    List<Book> books = null;

    @BeforeEach
    void setup() throws JsonProcessingException {
        author1 = new Author(1L,"Mateusz","Pioch");
        author2 = new Author(2L,"Grzegorz","Barka");
        genre1 = new Genre("obyczajowa");
        List<Book> books = List.of(
                new Book(1L,"W krainie deszcowcow", 12345L, "2019", "Polish", genre1, author2),
                new Book(1L,"O nastrojach w narodzie", 12645L, "2008", "Polish", genre1, author2),
                new Book(1L,"Osiem kwadrat", 12385L, "2007", "Polish", genre1, author2)
        );

        ObjectMapper mapper = new ObjectMapper();
        authorJson = mapper.writeValueAsString(author1);
    }

    @Test
    void findAllAuthors() throws Exception {
        when(authorRepository.findAll()).thenReturn(List.of(author1));

        mockMvc.perform(get("/author"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveAuthor() throws Exception {
        mockMvc.perform(post("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnAuthorWithMoreThanOneBook() throws Exception{
        when(authorRepository.findAll()).thenReturn(List.of(author2));
        mockMvc.perform(get("/author/moreThanBooks/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void shouldNotReturnAuthorWithOneBook() throws Exception{
        when(authorRepository.findAll()).thenReturn(List.of(author1));
        mockMvc.perform(get("/author/moreThanBooks/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}