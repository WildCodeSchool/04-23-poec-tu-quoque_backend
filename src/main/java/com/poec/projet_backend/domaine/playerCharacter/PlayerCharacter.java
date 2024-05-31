package com.poec.projet_backend.domaine.playerCharacter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.game_table.GameTable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "character_sheet_id")
    @JsonIgnoreProperties("characters")
    private CharacterSheet character_sheet;
}
