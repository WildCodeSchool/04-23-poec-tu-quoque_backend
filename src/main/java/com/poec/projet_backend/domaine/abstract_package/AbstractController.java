package com.poec.projet_backend.domaine.abstract_package;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractController<T> {

    @Autowired
    private AbstractService<T> service;

    @PostMapping("/add")
    public ResponseEntity<T> add(@RequestBody T entity) {
        T createdEntity = service.add(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
