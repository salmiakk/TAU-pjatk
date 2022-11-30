package com.pjatk.library_management_system.service;
import com.pjatk.library_management_system.model.Author;
import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Genre;
import com.pjatk.library_management_system.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;

    Author author1 = null;


    @BeforeEach
    void setup(){
        author1 = new Author("Mateusz","Pioch");
    }
    @Test
    void shouldReturnAuthorWithMoreThanOneBook(){
        Genre genre1 = new Genre("dramat");
        Genre genre2 = new Genre("obyczajowa");

        Book book1 = new Book(1L,"W krainie deszcowcow", 12345L, "2019", "Polish", genre1, author1);
        Book book2 = new Book(1L,"O nastrojach w narodzie", 15495L, "2008", "Polish", genre2, author1);

        doReturn(List.of(author1)).when(authorRepository).findAll();
        List<Author> authorsList = authorService.getAuthorsWithMoreThanBooks(1L);

        assertThat(authorsList).contains(author1);
    }
    @Test
    void deleteAuthorMethodIsInvoked() {
        authorRepository.deleteById(anyLong());
        verify(authorRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void shouldNotReturnAuthorWithZeroBooks(){
        List<Author> authorsList = authorService.getAuthorsWithMoreThanBooks(1L);

        assertThat(authorsList).doesNotContain(author1);
    }

}
