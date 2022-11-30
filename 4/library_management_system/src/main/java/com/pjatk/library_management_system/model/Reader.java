package com.pjatk.library_management_system.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader extends LibraryEntity{

    public Reader() {
    }

    public Reader(Long id, String firstName, String surName, Long pesel, Long telephone_no, String email) {
        super(id);
        this.firstName = firstName;
        this.surName = surName;
        this.pesel = pesel;
        this.telephone_no = telephone_no;
        this.email = email;
    }

    private String firstName;
    private String surName;
    private Long pesel;
    private Long telephone_no;
    private String email;


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

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public Long getTelephone_no() {
        return telephone_no;
    }

    public void setTelephone_no(Long telephone_no) {
        this.telephone_no = telephone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

