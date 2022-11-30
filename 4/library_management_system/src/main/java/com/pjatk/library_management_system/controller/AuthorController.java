package com.pjatk.library_management_system.controller;

import com.pjatk.library_management_system.model.Author;
import com.pjatk.library_management_system.service.AuthorService;
import com.pjatk.library_management_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController extends LibraryController<Author> {
    @Autowired
    private AuthorService authorService;

    @Override
    protected LibraryService<Author> getLibraryService() {
        return this.authorService;
    }

    @GetMapping("/moreThanBooks/{howMany}")
    public ResponseEntity<List<Author>> getAuthorsWithMoreThanBooks(@PathVariable Long howMany){
        List<Author> response = authorService.getAuthorsWithMoreThanBooks(howMany);
        if (response.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(response);
    }
}
