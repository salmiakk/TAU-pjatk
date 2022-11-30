package com.pjatk.library_management_system.controller;

import com.pjatk.library_management_system.model.Reader;
import com.pjatk.library_management_system.model.Rent;
import com.pjatk.library_management_system.service.LibraryService;
import com.pjatk.library_management_system.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController extends LibraryController<Rent>{
    @Autowired
    private RentService rentService;

    @Override
    protected LibraryService getLibraryService() {
        return this.rentService;
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Rent>> getRentsOverdue(){
        List<Rent> rents = rentService.getRentsOverdue();
        if(rents.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(rents);
    }
    @GetMapping("/dueTomorrow")
    public ResponseEntity<List<Rent>> getRentsDueTomorrow(){
        List<Rent> rents = rentService.getRentsDueTomorrow();
        if(rents.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(rents);
    }

}
