package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheetService;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatistics;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.Weapon;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SheetFixture {

    @Autowired
    private CharacterSheetService sheetService;

    public void load(PlayerCharacter player) {
        CharacterWeapons characterWeapons = new CharacterWeapons();
        Weapon weapon = new Weapon("dague");
        if(sheetService.getAll().isEmpty()) {
            CharacterSheet sheet = CharacterSheet.builder()
                    .age("15")
                    .god("Bob le bricolo")
                    .level("1")
                    .characterClass("Barbare")
                    .gender("M")
                    .characterName("Gerbert")
                    .characterRace("Elfe")
                    .eyesColor("bleux")
                    .hairColor("roses")
                    .alignment("Chaotique Bon")
                    .skinColor("blanche")
                    .stats(new CharacterStatistics())
                    .playerCharacter(player)
                    .skillInfoEnteredByPlayerList(null)
                    .weapons(new CharacterWeapons())
                    .heightModifierRolled("15")
                    .weightModifierRolled("15")
                    .build();

            this.sheetService.add(sheet);
        }
    }
}
