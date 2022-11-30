package com.pjatk.library_management_system.controller;

import com.pjatk.library_management_system.model.Genre;
import com.pjatk.library_management_system.service.GenreService;
import com.pjatk.library_management_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController extends LibraryController<Genre>{
    @Autowired
    private GenreService genreService;

    @Override
    protected LibraryService getLibraryService() {
        return this.genreService;
    }

}
