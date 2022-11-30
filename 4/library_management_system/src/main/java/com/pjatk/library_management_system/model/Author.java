package com.pjatk.library_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author extends LibraryEntity {

    public Author(Long id, String firstName, String surName) {
        super(id);
        this.firstName = firstName;
        this.surName = surName;
    }

    public Author(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public Author(Long id, String firstName, String surName, List<Book> bookList) {
        super(id);
        this.firstName = firstName;
        this.surName = surName;
        this.bookList = bookList;
    }

    private String firstName;
    private String surName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Author(){

    }
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "author")
    @JsonIgnore
    private List<Book> bookList = new ArrayList<>();
}
