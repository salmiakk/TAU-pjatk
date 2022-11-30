package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.*;
import com.pjatk.library_management_system.repository.BookRepository;
import com.pjatk.library_management_system.repository.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceTest.class);

    @Mock
    private RentRepository rentRepository;

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    Genre genre = null;
    Author author1 = null;
    Book book1 = null;
    Reader reader1 = null;

    @BeforeEach
    void setup(){
        genre = new Genre("obyczajowa");
        author1 = new Author("Mateusz","Pioch");
        book1 = new Book(1L,"W krainie deszcowcow", 12345L, "2019", "Polish", genre, author1);
        reader1 = new Reader(1L, "Grzegorz","Brzeczyszczykiewicz", 98205829384L, 739582058L, "grzegorz@wp.pl");
    }

    @Test
    void sameIsdnBookNotSaved() throws Exception {
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        Exception exception = assertThrows(Exception.class, () ->{
           bookService.save(book1);
        });
    }
    @Test
    void rentedBookIsShown(){
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book1, reader1)));
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        List<Book> books = bookService.getRentedBooks();
        assertThat(books).contains(book1);
    }
    @Test
    void deleteBookMethodIsInvoked() {
        bookRepository.deleteById(anyLong());
        verify(bookRepository,times(1)).deleteById(anyLong());
    }
    @Test
    void notRentedBooksIsNotShown(){
        Book book2 = new Book(1L,"Trzy gwiazdki", 9482223L, "2010", "Polish", genre, author1);
        when(rentRepository.findAll()).thenReturn(List.of(new Rent(1L,book1, reader1)));
        when(bookRepository.findAll()).thenReturn(List.of(book1,book2));
        List<Book> books = bookService.getRentedBooks();
        assertThat(books).doesNotContain(book2);
    }
}