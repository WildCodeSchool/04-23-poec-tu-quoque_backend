package com.poec.projet_backend.domaine.note;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableDTO;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import com.poec.projet_backend.util.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController extends AbstractController<Note> {

    @Autowired
    private NoteService service;

    @Autowired
    private UserAppService userAppService;

    @Autowired
    private GameTableService gameTableService;

    @Autowired
    private PlayerCharacterService playerCharacterService;

    @GetMapping("/get/all")
    public ResponseEntity<List<NoteDTO>> getAll() {
        List<Note> noteList= service.getAll();
        List<NoteDTO> noteDTOList = noteList.stream().map(NoteDTO::mapFromEntity).toList();
        return new ResponseEntity<>(noteDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/note/{noteId}")
    public ResponseEntity<NoteDTO> getById(@PathVariable("noteId") Long noteId) {
        Note foundNote = service.getById(noteId);
        NoteDTO noteDTO = NoteDTO.mapFromEntity(foundNote);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @PostMapping("/add/user/{userId}")
    ResponseEntity<NoteDTO> addToUser(@RequestBody Note note, @PathVariable Long userId) {
        UserApp foundUser = userAppService.getById(userId);
        note.setUser(foundUser);
        Note noteCreated = service.add(note);
        NoteDTO noteDTO = NoteDTO.mapFromEntity(noteCreated);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @PostMapping("/add/character/{characterId}")
    ResponseEntity<NoteDTO> addToCharacter(@RequestBody Note note, @PathVariable Long characterId) {
        PlayerCharacter foundCharacter = playerCharacterService.getById(characterId);
        note.setPlayer_character(foundCharacter);
        Note noteCreated = service.add(note);
        NoteDTO noteDTO = NoteDTO.mapFromEntity(noteCreated);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @PostMapping("/add/table/{tableId}")
    ResponseEntity<NoteDTO> addToTable(@RequestBody Note note, @PathVariable Long tableId) {
        GameTable foundGameTable = gameTableService.getById(tableId);
        note.setGame_table(foundGameTable);
        Note noteCreated = service.add(note);
        NoteDTO noteDTO = NoteDTO.mapFromEntity(noteCreated);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<NoteDTO> patchNote(@RequestBody Note incompleteNote, @PathVariable Long id) {
        Note foundNote = service.getById(id);

        try {
            Patcher.elementPatcher(foundNote, incompleteNote);
            service.add(foundNote);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(NoteDTO.mapFromEntity(foundNote), HttpStatus.OK);
    }
}
