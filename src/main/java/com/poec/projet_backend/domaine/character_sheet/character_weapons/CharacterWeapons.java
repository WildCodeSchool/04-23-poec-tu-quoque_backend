package com.poec.projet_backend.domaine.character_sheet.character_weapons;

import com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon.Weapon;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class CharacterWeapons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "characterWeapons")
    private List<Weapon> weapons = new ArrayList<Weapon>();

    public CharacterWeapons addWeapon(Weapon weapon) {
        weapons.add(weapon);
        return this;
    }
}
