package com.poec.projet_backend.domaine.character_sheet;

import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatisticsService;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.Weapon;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.WeaponDTO;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.WeaponService;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapons_dto_from_front.WeaponDTOFromFront;
import com.poec.projet_backend.domaine.character_sheet.purse.PurseService;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayer;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayerDTO;
import com.poec.projet_backend.domaine.character_sheet.skills.SkillInfoEnteredByPlayerService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateSheetService {

    @Autowired
    private CharacterSheetRepository sheetRepository;

    @Autowired
    private PlayerCharacterService characterService;

    @Autowired
    private SkillInfoEnteredByPlayerService skillService;

    @Autowired
    private PurseService purseService;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private CharacterStatisticsService statisticsService;

    public CharacterSheetDTO update(Long id, CharacterSheetDTOFromFront sheetDTO) {
        PlayerCharacter character = characterService.getCharacterByCharacterSheet(sheetDTO.id());
        CharacterSheet sheet = CharacterSheetDTOFromFront.mapFromDtoToEntityWithoutCharacterPlayer(sheetDTO, character);

        saveSkills(sheet);

        saveWeapons(sheetDTO, sheet);

        savePurse(sheet);

        statisticsService.update(sheet.getStats().getId(), sheet.getStats());

        sheetRepository.save(sheet);
        return CharacterSheetDTO.mapFromEntity(sheet);
    }

    private void saveWeapons(CharacterSheetDTOFromFront sheetDTO, CharacterSheet sheet) {
        List<Weapon> weapons = new ArrayList<>();
        for(WeaponDTOFromFront weaponDTO: sheetDTO.weapons()) {
            Weapon weapon = WeaponDTOFromFront.mapFromDtoToEntity(weaponDTO);
            Weapon weaponSaved;

            if(weaponDTO.id() == -1) {
                weaponSaved = weaponService.add(weapon);
            } else {
                weaponSaved = weaponService.update(weapon.getId(), weapon);
            }
            weaponSaved.setCharacterWeapons(sheet.getWeapons());
            weapons.add(weaponSaved);
        }

        sheet.getWeapons().setWeapons(weapons);
    }

    private void saveSkills(CharacterSheet sheet) {
        List<SkillInfoEnteredByPlayer> newSkills = new ArrayList<>();
        for(SkillInfoEnteredByPlayer skill: sheet.getSkillInfoEnteredByPlayerList()) {
            skill.setSheet(sheet);
            SkillInfoEnteredByPlayer newSkill;
            System.err.println("skill : " + skill.getSkillId() + " -> rank : " + skill.getRankSkill() );
            if(skill.getId() == -1) {
                newSkill =  skillService.add(skill);
            } else {
                newSkill = skillService.update(skill.getId(), skill);
            }
            newSkills.add(newSkill);
        }

        sheet.setSkillInfoEnteredByPlayerList(newSkills);
    }

    private void savePurse(CharacterSheet sheet) {
        if(sheet.getPurse().getId() == -1) {
            purseService.add(sheet.getPurse());
        } else {
            purseService.update(sheet.getPurse().getId(), sheet.getPurse());
        }
    }
}

