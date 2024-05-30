package com.poec.projet_backend.domaine.character;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {

    @Autowired
    private final CharacterRepository repository;

    public List<Character> getAll() {
        return repository.findAll();
    }
}
