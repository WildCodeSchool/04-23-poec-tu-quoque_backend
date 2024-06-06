package com.poec.projet_backend.domaine.character_sheet;

public record CharacterSheetDTO(
        Long id,
        String playerName,
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
        String weightModifierRolled
) {
    public static CharacterSheetDTO mapFromEntity(CharacterSheet sheet) {
        return new CharacterSheetDTO(
                sheet.getId(),
                sheet.getPlayerCharacter().getName(),
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
                sheet.getWeightModifierRolled()
        );

    }
}
