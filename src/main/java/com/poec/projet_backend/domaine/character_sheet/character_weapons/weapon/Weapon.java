package com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon;

import com.poec.projet_backend.domaine.character_sheet.character_weapons.CharacterWeapons;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "character_weapons_id")
    private CharacterWeapons characterWeapons;

    public Weapon(String name) {
        this.name = name;
    }
}
