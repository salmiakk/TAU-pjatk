package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService extends LibraryService<Author> {
    @Autowired
    private JpaRepository<Author, Long> authorRepository;

    @Override
    public JpaRepository<Author, Long> getRepository() {
        return this.authorRepository;
    }

    public List<Author> getAuthorsWithMoreThanBooks(Long howMany){
        return this.findAll().stream()
                .filter(a -> a.getBookList().size() > howMany)
                .collect(Collectors.toList());
    }

}
