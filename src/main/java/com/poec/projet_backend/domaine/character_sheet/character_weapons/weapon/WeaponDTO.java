package com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon;

public record WeaponDTO(
        Long id,
        String name
) {
    public static WeaponDTO mapFromEntity(Weapon weapon) {
        return new WeaponDTO(
                weapon.getId(),
                weapon.getName()
        );
    }
}
