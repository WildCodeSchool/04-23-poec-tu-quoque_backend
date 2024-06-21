package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.note.NoteService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesFixtures {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserAppService userService;
    @Autowired
    private GameTableService tableService;
    @Autowired
    private PlayerCharacterService characterService;

    public void load() {
        generate();
    }

    private void generate() {
        UserApp user = userService.getById(3L);
        Note note = Note.builder()
                .name("Disponibilités")
                .text("29 et 30 juin toute la journée")
                .user(user)
                .build();
        //List<Note> userNotes = user.getNotes(); unused since next line error
        // userNotes.add(note);
        // => failed to lazily initialize a collection of role
        //
        // SOLUTION => change cascade type in Note.java from ALL to MERGE

        // not needed in this configuration
        //user.setNotes(userNotes);
        //userService.update(user.getId(), user);
        noteService.add(note);

        UserApp user1 = userService.getById(3L);
        Note note1 = Note.builder()
                .name("Eureka")
                .text("42")
                .user(user1)
                .build();
        noteService.add(note1);

        GameTable table2 = tableService.getById(3L);
        Note note2 = Note.builder()
                .name("mot de passe")
                .text("Ha ha ha !!!")
                .gameTable(table2)
                .build();
        noteService.add(note2);

        GameTable table3 = tableService.getById(3L);
        Note note3 = Note.builder()
                .name("pas d'inspi")
                .text("Mur")
                .gameTable(table3)
                .build();
        noteService.add(note3);

        PlayerCharacter character4 = characterService.getById(1L);
        Note note4 = Note.builder()
                .name("Météo")
                .text("Beau temps")
                .playerCharacter(character4)
                .build();
        noteService.add(note4);

        Note note5 = Note.builder()
                .name("Secret inavouable")
                .text("oubliés")
                .playerCharacter(character4)
                .build();
        noteService.add(note5);
    }
}
