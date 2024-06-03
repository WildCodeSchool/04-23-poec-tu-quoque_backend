package com.poec.projet_backend.domaine.abstract_package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractController<T> {

    @Autowired
    private AbstractService<T> service;

    @GetMapping ("/get/all")
    public ResponseEntity<List<T>> getAll() {
        List<T> entityList = service.getAll();
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") Long id) {
        T searchedEntity = service.getById(id);
        return new ResponseEntity<>(searchedEntity, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<T> add(@RequestBody T entity) {
        T createdEntity = service.add(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T entity) {
        T updatedEntity = service.update(id, entity);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
