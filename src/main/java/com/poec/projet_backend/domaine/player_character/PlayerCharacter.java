package com.poec.projet_backend.domaine.player_character;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.calendar_event.CalendarEvent;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;
    private boolean accepted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_table_id")
    @JsonIgnoreProperties("characters")
    private GameTable game_table;

    @OneToOne(mappedBy = "player_character")
    @JsonIgnoreProperties("character")
    private CharacterSheet character_sheet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_event_id")
    @JsonIgnoreProperties("characters")
    private CalendarEvent calendar_event;

    @OneToMany(mappedBy = "player_character")
    @JsonIgnoreProperties("player_character")
    private List<Note> notes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("player_characters")
    private UserApp user;
}
