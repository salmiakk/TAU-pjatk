package com.pjatk.library_management_system.repository;

import com.pjatk.library_management_system.model.Reader;
import com.pjatk.library_management_system.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
}
