package com.pjatk.library_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Rent extends LibraryEntity{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="reader_id")
    private Reader reader;

    @JsonIgnore
    private LocalDate rentedDate = LocalDate.now();
    @JsonIgnore
    private LocalDate dueDate = rentedDate.minusDays(14);


    public Rent(Long id, Book book, Reader reader) {
        super(id);
        this.book = book;
        this.reader = reader;
    }
    public Rent(Long id, Book book, Reader reader, LocalDate dueDate) {
        super(id);
        this.book = book;
        this.reader = reader;
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public Rent() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
