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

    @ManyToOne
    @JoinColumn(name = "sheet_id")
    private CharacterSheet sheet;

    public SkillInfoEnteredByPlayer(int skillId, int rankSkill) {
        this.skillId = skillId;
        this.rankSkill = rankSkill;
    }

    public SkillInfoEnteredByPlayer(int skillId, int rankSkill, String complement) {
        this.skillId = skillId;
        this.rankSkill = rankSkill;
        this.complement = complement;
    }

}
