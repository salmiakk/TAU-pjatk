package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.*;
import com.pjatk.library_management_system.repository.BookRepository;
import com.pjatk.library_management_system.repository.ReaderRepository;
import com.pjatk.library_management_system.repository.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {
    @Mock
    private ReaderRepository readerRepository;
    @Mock
    private RentRepository rentRepository;
    @InjectMocks
    private ReaderService readerService;

    Reader reader1 = null;
    Reader reader2 = null;
    Book book2 = null;
    Author author1 = null;
    Genre genre1 = null;

    @BeforeEach
    void setup(){
        reader1 = mock(Reader.class);
        reader2 = mock(Reader.class);
        author1 = mock(Author.class);
        genre1 = mock(Genre.class);
        book2 = new Book(1L,"Trzy gwiazdki", 9482223L, "2010", "Polish", genre1, author1);
    }
    @Test
    void deleteReaderMethodIsInvoked() {
        readerRepository.deleteById(anyLong());
        verify(readerRepository,times(1)).deleteById(anyLong());
    }
    @Test
    void samePeselReaderNotSaved(){
        when(readerService.findAll()).thenReturn(List.of(reader1));
        Exception exception = assertThrows(Exception.class, () ->{
            readerService.save(reader1);
        });
    }
    @Test
    void readersWithRentsAreShown(){
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book2, reader1)));
        when(readerRepository.findAll()).thenReturn(List.of(reader1));
        List <Reader> readers = readerService.getReadersWithRents();
        assertThat(readers).contains(reader1);
    }
    @Test
    void readersWithoutRentsAreNotShown(){
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book2, reader1)));
        when(readerRepository.findAll()).thenReturn(List.of(reader1));
        List <Reader> readers = readerService.getReadersWithRents();
        assertThat(readers).doesNotContain(reader2);
    }
}