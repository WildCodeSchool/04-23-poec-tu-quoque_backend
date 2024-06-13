package com.poec.projet_backend.domaine.character_sheet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatistics;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import com.poec.projet_backend.domaine.character_sheet.purse.Purse;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayer;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharacterSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "characterSheet")
    @JoinColumn(name = "character_id")
    @JsonIgnoreProperties("characterSheet")
    private PlayerCharacter playerCharacter;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sheet")
    @JsonIgnoreProperties("sheet")
    private List<SkillInfoEnteredByPlayer> skillInfoEnteredByPlayerList;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics")
    private CharacterStatistics stats;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "purse")
    private Purse purse;

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
    private String skinColor;
    private String weightModifierRolled;

    static public CharacterSheet createBlank() {
        return CharacterSheet.builder()
                .stats(CharacterStatistics.createBlank())
                .weapons(new CharacterWeapons())
                .purse(new Purse())
                .build();
    }

    CharacterSheet(CharacterStatistics stats, CharacterWeapons weapons) {
        this.stats = stats;
        this.weapons = weapons;
    }
}
