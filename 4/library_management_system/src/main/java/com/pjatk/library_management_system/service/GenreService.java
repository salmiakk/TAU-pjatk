package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends LibraryService<Genre>{
    @Autowired
    private JpaRepository<Genre, Long> genreRepository;

    @Override
    public JpaRepository<Genre, Long> getRepository() {
        return this.genreRepository;
    }
}
