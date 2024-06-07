package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheetService;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatistics;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic.Statistic;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.Weapon;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayer;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheetFixture {

    @Autowired
    private CharacterSheetService sheetService;

    public void load(PlayerCharacter player) {
        if(sheetService.getAll().isEmpty()) {
            this.sheetService.add(generateSheet(player));
        }
    }

    private CharacterSheet generateSheet(PlayerCharacter player) {

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
                .stats(generateCharacterStatistics())
                .playerCharacter(player)
                .skillInfoEnteredByPlayerList(generateSkills())
                .weapons(generateWeapons())
                .heightModifierRolled("15")
                .weightModifierRolled("15")
                .build();

        System.err.println(sheet.getSkillInfoEnteredByPlayerList().toString());

        List<SkillInfoEnteredByPlayer> skillList = sheet.getSkillInfoEnteredByPlayerList();
        for (SkillInfoEnteredByPlayer skill : skillList) {
            skill.setSheet(sheet);
        }

        CharacterWeapons weapons = sheet.getWeapons();
        List<Weapon> weaponList = weapons.getWeapons();
        for (Weapon weapon: weaponList) {
            weapon.setCharacterWeapons(weapons);
        }



        return sheet;
    }

    private CharacterStatistics generateCharacterStatistics() {
        Statistic FOR = new Statistic(14, 0, "FOR");
        Statistic DEX = new Statistic(13, 0, "DEX");
        Statistic CON = new Statistic(12, 0, "CON");
        Statistic INT = new Statistic(11, 0, "INT");
        Statistic SAG = new Statistic(16, 0, "SAG");
        Statistic CHA = new Statistic(18, 0, "CHA");

        return new CharacterStatistics(FOR, DEX, CON, INT, SAG, CHA);
    }

    private CharacterWeapons generateWeapons() {
        CharacterWeapons characterWeapons = new CharacterWeapons();
        Weapon weapon = new Weapon("dague");
        characterWeapons.addWeapon(weapon);
        return characterWeapons;
    }

    private List<SkillInfoEnteredByPlayer> generateSkills() {
        SkillInfoEnteredByPlayer acrobaties = new SkillInfoEnteredByPlayer(1, 4);
        SkillInfoEnteredByPlayer craft = new SkillInfoEnteredByPlayer(4, 4, "Forge");

        ArrayList<SkillInfoEnteredByPlayer> skills = new ArrayList<>();
        skills.add(acrobaties);
        skills.add(craft);
        return skills;
    }
}
