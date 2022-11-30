package com.pjatk.library_management_system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjatk.library_management_system.model.*;
import com.pjatk.library_management_system.repository.AuthorRepository;
import com.pjatk.library_management_system.repository.BookRepository;
import com.pjatk.library_management_system.repository.RentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private RentRepository rentRepository;

    String bookJson = null;
    Book book1 = null;
    Author author1 = null;
    Genre genre1 = null;
    Reader reader1 = null;

    @BeforeEach
    void setup() throws JsonProcessingException {
        genre1 = new Genre(1L,"obyczajowa");
        author1 = new Author(1L,"Mateusz","Pioch");
        book1 = new Book(1L,"W krainie deszcowcow", 12345L, "2019", "Polish", genre1, author1);
        reader1 = new Reader(1L, "Grzegorz","Brzeczyszczykiewicz", 98205829384L, 739582058L, "grzegorz@wp.pl");

        ObjectMapper mapper = new ObjectMapper();
        bookJson = mapper.writeValueAsString(book1);
    }

    @Test
    void findAllBooks() throws Exception {
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        mockMvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveBook() throws Exception {
        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void rentedBookIsShown() throws Exception{
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book1, reader1)));
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        mockMvc.perform(get("/book/rented"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"title\":\"W krainie deszcowcow\"," +
                        "\"isdn\":12345,\"year_written\":\"2019\",\"language\":\"Polish\",\"genre\":{\"id\":1,\"name\":" +
                        "\"obyczajowa\"},\"author\":{\"id\":1,\"firstName\":\"Mateusz\",\"surName\":\"Pioch\"}}]"));
    }
    @Test
    void notRentedBookIsNotShown() throws Exception{
        Book book2 = new Book(1L,"Trzy gwiazdki", 9482223L, "2010", "Polish", genre1, author1);
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book1, reader1)));
        when(bookRepository.findAll()).thenReturn(List.of(book2));
        mockMvc.perform(get("/book/rented"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":1,\"title\":\"W krainie deszcowcow\"," +
                    "\"isdn\":12345,\"year_written\":\"2019\",\"language\":\"Polish\",\"genre\":{\"id\":1,\"name\":" +
                    "\"obyczajowa\"},\"author\":{\"id\":1,\"firstName\":\"Mateusz\",\"surName\":\"Pioch\"}}]"));
    }

}