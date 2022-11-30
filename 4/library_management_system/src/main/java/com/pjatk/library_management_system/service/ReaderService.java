package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Reader;
import com.pjatk.library_management_system.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderService extends LibraryService<Reader>{
    @Autowired
    private JpaRepository<Reader, Long> readerRepository;

    @Autowired
    private JpaRepository<Rent, Long> rentRepository;

    @Override
    public JpaRepository<Reader, Long> getRepository() {
        return this.readerRepository;
    }

    @Override
    public Reader save(Reader reader) throws Exception {
        boolean isPresent = findAll().stream()
                .map(o -> o.getPesel().equals(reader.getPesel()))
                .findFirst()
                .orElse(false);
        if (isPresent) {
            throw new Exception("Reader with this PESEL already exists!");
        } else {
            return readerRepository.save(reader);
        }
    }
    public List<Reader> getReadersWithRents(){
        return this.readerRepository.findAll().stream()
                .filter(two -> rentRepository.findAll().stream()
                        .anyMatch(one -> one.getReader().getPesel().equals(two.getPesel()))
                )
                .collect(Collectors.toList());
    }
    public List<Reader> getReadersWithoutRents(){
        return this.readerRepository.findAll().stream()
                .filter(two -> rentRepository.findAll().stream()
                        .noneMatch(one -> one.getReader().getPesel().equals(two.getPesel()))
                )
                .collect(Collectors.toList());
    }
}
