package com.poec.projet_backend.domaine.character_sheet;

import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatisticDTO;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatistics;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeaponsDTO;
import com.poec.projet_backend.domaine.character_sheet.purse.Purse;
import com.poec.projet_backend.domaine.character_sheet.purse.PurseDTO;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayer;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayerDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public record CharacterSheetDTO(
        Long id,
        String age,
        String alignment,
        String characterClass,
        String characterName,
        String characterRace,
        String eyesColor,
        String gender,
        String god,
        String hairColor,
        String heightModifierRolled,
        String level,
        String skinColor,
        String weightModifierRolled,
        String playerName,
        CharacterWeaponsDTO weapons,
        List<SkillInfoEnteredByPlayerDTO> skills,
        CharacterStatisticDTO stats,
        PurseDTO purse
) {
    public static CharacterSheetDTO mapFromEntity(CharacterSheet sheet) {
        return new CharacterSheetDTO(
                sheet.getId(),
                sheet.getAge(),
                sheet.getAlignment(),
                sheet.getCharacterClass(),
                sheet.getCharacterName(),
                sheet.getCharacterRace(),
                sheet.getEyesColor(),
                sheet.getGender(),
                sheet.getGod(),
                sheet.getHairColor(),
                sheet.getHeightModifierRolled(),
                sheet.getLevel(),
                sheet.getSkinColor(),
                sheet.getWeightModifierRolled(),
                sheet.getPlayerCharacter().getUser().getNickname(),
                CharacterWeaponsDTO.mapFromEntity(sheet.getWeapons()),
                sheet.getSkillInfoEnteredByPlayerList().stream().map(SkillInfoEnteredByPlayerDTO::mapFromEntity).toList(),
                CharacterStatisticDTO.mapFromEntity(sheet.getStats()),
                PurseDTO.mapFromEntity(sheet.getPurse())
        );
    }
}
