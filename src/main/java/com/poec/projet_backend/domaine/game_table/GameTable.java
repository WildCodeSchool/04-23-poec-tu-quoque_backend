package com.poec.projet_backend.domaine.game_table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.playerCharacter.PlayerCharacter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class GameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;

    @OneToMany(mappedBy = "game_table")
    @JsonIgnoreProperties("game_table")
    private List<PlayerCharacter> playerCharacters;
}
