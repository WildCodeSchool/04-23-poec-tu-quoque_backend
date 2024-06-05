package com.poec.projet_backend.domaine.character_sheet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatistics;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayer;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class CharacterSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private PlayerCharacter player_character;

    @OneToMany(mappedBy = "sheet")
    private List<SkillInfoEnteredByPlayer> skillInfoEnteredByPlayerList;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics")
    private CharacterStatistics stats;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "weapons")
    private CharacterWeapons weapons;

    private String age;
    private String alignment;
    private String characterClass;
    private String characterName;
    private String characterRace;
    private String eyesColor;
    private String gender;
    private String god;
    private String hairColor;
    private String heightModifierRolled;
    private String level;
    private String playerName;
    private String skinColor;
    private String weightModifierRolled;
}
