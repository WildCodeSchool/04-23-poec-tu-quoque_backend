package com.poec.projet_backend.domaine.character_sheet.skills;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class SkillInfoEnteredByPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int skillId;
    private int rankSkill;
    private String complement;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sheet_id")
    private CharacterSheet sheet;
}
