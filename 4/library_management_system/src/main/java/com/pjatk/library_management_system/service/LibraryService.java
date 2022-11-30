package com.pjatk.library_management_system.service;

import com.pjatk.library_management_system.model.LibraryEntity;
import org.apache.tomcat.jni.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class LibraryService<T extends LibraryEntity> {
    public abstract JpaRepository<T, Long> getRepository();
    public List<T> findAll(){
        return getRepository().findAll();
    };
    public T save(T t) throws Exception {
        return getRepository().save(t);
    };
    public boolean deleteByIdIfPresent(Long id){
        boolean isPresent = findAll()
                .stream()
                .map(o -> o.getId().equals(id))
                .findFirst()
                .orElse(false);
        if(isPresent){
            getRepository().deleteById(id);
            return true;
        }else return false;
    };
}
