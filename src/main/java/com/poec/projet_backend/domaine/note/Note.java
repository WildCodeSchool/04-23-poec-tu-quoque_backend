package com.poec.projet_backend.domaine.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.playerCharacter.PlayerCharacter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_table_id")
    @JsonIgnoreProperties("notes")
    private GameTable game_table;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_character_id")
    @JsonIgnoreProperties("notes")
    private PlayerCharacter player_character;
}
