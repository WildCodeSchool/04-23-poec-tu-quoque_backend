package com.poec.projet_backend.domaine.character_sheet;

import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatisticDTO;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeaponsDTO;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapons_dto_from_front.WeaponDTOFromFront;
import com.poec.projet_backend.domaine.character_sheet.purse.PurseDTO;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayerDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;

import java.util.List;

public record CharacterSheetDTOFromFront(
        Long id,
        Long statisticsId,
        Long weaponsId,
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
        String playerName,
        PurseDTO purse,
        List<SkillInfoEnteredByPlayerDTO> skills,
        String skinColor,
        CharacterStatisticDTO stats,
        List<WeaponDTOFromFront> weapons,
        String weightModifierRolled
) {
    public static CharacterSheet mapFromDtoToEntityWithoutCharacterPlayer(CharacterSheetDTOFromFront sheetDTO, PlayerCharacter character) {
        return CharacterSheet.builder()
                .id(sheetDTO.id())
                .age(sheetDTO.age())
                .alignment(sheetDTO.alignment())
                .characterClass(sheetDTO.characterClass())
                .characterName(sheetDTO.characterName())
                .characterRace(sheetDTO.characterRace())
                .eyesColor(sheetDTO.eyesColor())
                .gender(sheetDTO.gender())
                .god(sheetDTO.god())
                .hairColor(sheetDTO.hairColor())
                .heightModifierRolled(sheetDTO.heightModifierRolled())
                .level(sheetDTO.level())
                .skinColor(sheetDTO.skinColor())
                .weightModifierRolled(sheetDTO.weightModifierRolled())
                .playerCharacter(character)
                .weapons(CharacterWeaponsDTO.mapFromDtoToEntity(sheetDTO.weaponsId(), sheetDTO.weapons()))
                .skillInfoEnteredByPlayerList(sheetDTO.skills().stream().map(SkillInfoEnteredByPlayerDTO::mapFromDtoToEntity).toList())
                .stats(CharacterStatisticDTO.mapFromDtoToEntity(sheetDTO.stats(), sheetDTO.statisticsId()))
                .purse(PurseDTO.mapFromDtoToEntity(sheetDTO.purse()))
                .build();
    }
}


