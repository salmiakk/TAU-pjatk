package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.*;
import com.pjatk.library_management_system.repository.RentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentServiceTest {
    @Mock
    private RentRepository rentRepository;
    @InjectMocks
    private RentService rentService;

    Rent rent1 = null;
    Reader reader1 = null;
    Book book1 = null;
    Genre genre1 = null;
    Author author1 = null;

    @BeforeEach
    void setup(){
        reader1 = new Reader(1L, "Grzegorz","Brzeczyszczykiewicz", 98205829384L, 739582058L, "grzegorz@wp.pl");
        genre1 = new Genre("obyczajowa");
        author1 = new Author("Mateusz","Pioch");
        book1 = new Book(1L,"W krainie deszcowcow", 12345L, "2019", "Polish", genre1, author1);
        rent1 = new Rent(1L, book1, reader1, LocalDate.of(2021,01,24));
    }
    @Test
    void deleteRentMethodIsInvoked() {
        rentRepository.deleteById(anyLong());
        verify(rentRepository,times(1)).deleteById(anyLong());
    }
    @Test
    void sameBookNotRentedTwice(){
        when(rentService.findAll()).thenReturn(List.of(rent1));
        Exception exception = assertThrows(Exception.class, () ->{
            rentService.save(rent1);
        });
    }
    @Test
    void rentsNotDueTomorrowAreNotShown(){
        Rent rent2 = new Rent(2L, book1, reader1, LocalDate.of(2020,01,24));

        when(rentService.findAll()).thenReturn(List.of(rent2));
        List<Rent> rents = rentService.getRentsDueTomorrow();
        assertThat(rents).doesNotContain(rent2);
    }
    @Test
    void rentsNotOverdueAreNotShown(){
        Rent rent3 = new Rent(2L, book1, reader1, LocalDate.of(2021,02,04));

        when(rentService.findAll()).thenReturn(List.of(rent3));
        List<Rent> rents = rentService.getRentsDueTomorrow();
        assertThat(rents).doesNotContain(rent3);
    }
}