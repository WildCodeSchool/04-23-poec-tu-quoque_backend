package com.poec.projet_backend.domaine.note;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController extends AbstractController<Note> {

    @Autowired
    private NoteService service;
}
