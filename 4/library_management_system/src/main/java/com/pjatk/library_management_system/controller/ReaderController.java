package com.pjatk.library_management_system.controller;

import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Reader;
import com.pjatk.library_management_system.service.LibraryService;
import com.pjatk.library_management_system.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController extends LibraryController<Reader>{
    @Autowired
    private ReaderService readerService;

    @Override
    protected LibraryService getLibraryService() {
        return this.readerService;
    }

    @GetMapping("/withRents")
    public ResponseEntity<List<Reader>> getReadersWithRents(){
        List<Reader> readers = readerService.getReadersWithRents();
        if(readers.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(readers);
    }
    @GetMapping("/withoutRents")
    public ResponseEntity<List<Reader>> getReadersWithoutRents(){
        List<Reader> readers = readerService.getReadersWithRents();
        if(readers.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(readers);
    }
}
