package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService extends LibraryService<Book> {
    @Autowired
    private JpaRepository<Book, Long> bookRepository;

    @Autowired
    private JpaRepository<Rent, Long> rentRepository;

    @Override
    public JpaRepository<Book, Long> getRepository() {
        return this.bookRepository;
    }

    @Override
    public Book save(Book book) throws Exception {
        boolean isPresent = findAll().stream()
                .map(o -> o.getIsdn().equals(book.getIsdn()))
                .findFirst()
                .orElse(false);
        if (isPresent) throw new Exception("Book with this ISDN already present!");
        else return bookRepository.save(book);
    }

    public List<Book> getRentedBooks(){
        List<Book> rentedBooks = rentRepository.findAll().stream()
                .map(one -> one.getBook())
                .filter(two -> bookRepository.findAll().stream()
                        .anyMatch(one -> one.getId().equals(two.getId()))
                )
                .collect(Collectors.toList());
        return rentedBooks;
    }

}
