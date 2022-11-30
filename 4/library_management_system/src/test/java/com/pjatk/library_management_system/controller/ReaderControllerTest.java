package com.pjatk.library_management_system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjatk.library_management_system.model.Author;
import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Reader;
import com.pjatk.library_management_system.model.Rent;
import com.pjatk.library_management_system.repository.AuthorRepository;
import com.pjatk.library_management_system.repository.ReaderRepository;
import com.pjatk.library_management_system.repository.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
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
class ReaderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReaderRepository readerRepository;

    @MockBean
    private RentRepository rentRepository;

    String readerJson = null;
    Reader reader1 = null;
    Book book = null;
    Reader reader2 = null;

    @BeforeEach
    void setup() throws JsonProcessingException {
        reader2 = mock(Reader.class);
        book = mock(Book.class);
        reader1 = new Reader(1L, "Grzegorz","Brzeczyszczykiewicz", 98205829384L, 739582058L, "grzegorz@wp.pl");
        ObjectMapper mapper = new ObjectMapper();
        readerJson = mapper.writeValueAsString(reader1);
    }

    @Test
    void findAll() throws Exception {
        when(readerRepository.findAll()).thenReturn(List.of(reader1));
        mockMvc.perform(get("/reader"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void saveReader() throws Exception {
        mockMvc.perform(post("/reader")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readerJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void notFoundIsReturnedWhenNoReaders() throws Exception{
        when(readerRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reader"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void readersWithRentsAreShown() throws Exception{

        when(readerRepository.findAll()).thenReturn(List.of(reader1));
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book,reader1)));
        mockMvc.perform(get("/reader/withRents"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Grzegorz\",\"surName\":\"Brzeczyszczykiewicz\"" +
                        ",\"pesel\":98205829384,\"telephone_no\":739582058,\"email\":\"grzegorz@wp.pl\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    void readersWithoutRentsAreNotShown() throws Exception{
        when(readerRepository.findAll()).thenReturn(List.of(reader1,reader2));
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book,reader1)));
        mockMvc.perform(get("/reader/withRents"))
                .andDo(print())
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Grzegorz\",\"surName\":\"Brzeczyszczykiewicz\"" +
                        ",\"pesel\":98205829384,\"telephone_no\":739582058,\"email\":\"grzegorz@wp.pl\"}]"))
                .andExpect(status().isOk());
    }
    @Test
    void notFoundIsReturnedWhenNoReadersWithRents() throws Exception{
        when(readerRepository.findAll()).thenReturn(List.of(reader1,reader2));
        when(rentRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reader/withRents"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}