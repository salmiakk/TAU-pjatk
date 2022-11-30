package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.Book;
import com.pjatk.library_management_system.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentService extends LibraryService<Rent>{
    @Autowired
    private JpaRepository<Rent, Long> rentRepository;

    @Override
    public JpaRepository<Rent, Long> getRepository() {
        return this.rentRepository;
    }

    @Override
    public Rent save(Rent rent) throws Exception {
        boolean isPresent = findAll().stream()
                .map(o -> o.getBook().getId().equals(rent.getBook().getId()))
                .findFirst()
                .orElse(false);
        if (isPresent) {
            throw new Exception("Book with such ID already rented!");
        }else{
            return rentRepository.save(rent);
        }
    }
    public List<Rent> getRentsOverdue(){
        List<Rent> rents = this.findAll().stream()
                .filter(r -> {
                    System.out.println(r.getDueDate().isAfter(LocalDate.now()));
                    return r.getDueDate().isAfter(LocalDate.now());
                })
                .collect(Collectors.toList());
        return rents;

    }
    public List<Rent> getRentsDueTomorrow(){
        return this.findAll().stream()
                .filter(r -> r.getDueDate().isAfter(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());
    }
}
