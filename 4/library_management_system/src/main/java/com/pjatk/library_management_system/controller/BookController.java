package com.pjatk.library_management_system.controller;

import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.service.BookService;
import com.pjatk.library_management_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController extends LibraryController<Book>{
    @Autowired
    private BookService bookService;

    @Override
    protected LibraryService getLibraryService() {
        return this.bookService;
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Book>> getRentedBooks(){
        List<Book> rentedBooks = bookService.getRentedBooks();
        if(rentedBooks.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(rentedBooks);
    }
}
