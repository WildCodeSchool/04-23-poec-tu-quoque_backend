package com.poec.projet_backend.domaine.character;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/character")
@RequiredArgsConstructor
public class CharacterController {

    @Autowired
    private final CharacterService characterService;

    @GetMapping("/get/all")
    public ResponseEntity<List<Character>> getAll(){
        return new ResponseEntity<List<Character>>(characterService.getAll(), HttpStatus.OK);
    }
}
