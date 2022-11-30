package com.pjatk.library_management_system.controller;

import com.pjatk.library_management_system.model.LibraryEntity;
import com.pjatk.library_management_system.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class LibraryController<T extends LibraryEntity>{

    protected abstract LibraryService<T> getLibraryService();

    @PostMapping
    protected T save(@RequestBody T t) throws Exception {
        return getLibraryService().save(t);
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll(){
        if(getLibraryService().getRepository().findAll().isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(this.getLibraryService().findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return getLibraryService().deleteByIdIfPresent(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
