package com.poec.projet_backend.domaine.character_sheet.character_weapons;

import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.Weapon;
import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.WeaponDTO;

import java.util.List;

public record CharacterWeaponsDTO(
        Long id,
        List<WeaponDTO> weapons
) {
    public static CharacterWeaponsDTO mapFromEntity(CharacterWeapons characterWeapons) {
        return new CharacterWeaponsDTO(
                characterWeapons.getId(),
                characterWeapons.getWeapons().stream().map(WeaponDTO::mapFromEntity).toList()
        );
    }
}
