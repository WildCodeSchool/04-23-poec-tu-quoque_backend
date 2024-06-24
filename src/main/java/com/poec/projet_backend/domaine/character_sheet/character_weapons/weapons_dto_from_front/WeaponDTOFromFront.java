package com.poec.projet_backend.domaine.character_sheet.character_weapons.weapons_dto_from_front;

import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.Weapon;

public record WeaponDTOFromFront(
        Long id,
        String name
) {
    public static Weapon mapFromDtoToEntity(WeaponDTOFromFront weaponDTO) {
        return new Weapon(weaponDTO.id(), weaponDTO.name());
    }
}
