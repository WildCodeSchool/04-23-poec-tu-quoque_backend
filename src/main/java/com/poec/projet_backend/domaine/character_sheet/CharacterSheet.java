package com.poec.projet_backend.domaine.character_sheet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.playerCharacter.PlayerCharacter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class CharacterSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "character_sheet")
    @JsonIgnoreProperties("character_sheet")
    private List<PlayerCharacter> playerCharacters;
}
