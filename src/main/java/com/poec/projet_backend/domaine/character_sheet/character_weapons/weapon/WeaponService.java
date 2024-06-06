package com.poec.projet_backend.domaine.character_sheet.character_weapons.weapon;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class WeaponService extends AbstractService<Weapon> {
    public WeaponService(JpaRepository<Weapon, Long> repository) {
        super(repository);
    }

    @Override
    public Weapon update(Long id, Weapon entity) {
        return null;
    }
}
