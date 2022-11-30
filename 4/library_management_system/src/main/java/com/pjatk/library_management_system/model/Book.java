package com.pjatk.library_management_system.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
public class Book extends LibraryEntity {

    private String title;
    private Long isdn;
    private String year_written;
    private String language;

    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @ManyToOne()
    private Genre genre;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne()
    private Author author;

    public Book() {
    }

    public Book(Long id, String title, Long isdn, String year_written, String language, Genre genre, Author author) {
        super(id);
        this.title = title;
        this.isdn = isdn;
        this.year_written = year_written;
        this.language = language;
        this.genre = genre;
        this.author = author;
        author.getBookList().add(this);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIsdn() {
        return isdn;
    }

    public void setIsdn(Long isdn) {
        this.isdn = isdn;
    }

    public String getYear_written() {
        return year_written;
    }

    public void setYear_written(String year_written) {
        this.year_written = year_written;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }



}
