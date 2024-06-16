package com.poec.projet_backend.domaine.player_character;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.calendar_event.CalendarEvent;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;
    private boolean accepted;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "game_table_id")
    @JsonIgnoreProperties("characters")
    private GameTable gameTable;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "sheet_id")
    @JsonIgnoreProperties("playerCharacter")
    private CharacterSheet characterSheet;

    @OneToMany(mappedBy = "playerCharacter")
    @JsonIgnoreProperties("player_character")
    private List<CalendarEvent> calendarEvents;

    @OneToMany(mappedBy = "playerCharacter")
    @JsonIgnoreProperties("player_character")
    private List<Note> notes;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("player_characters")
    private UserApp user;

}
