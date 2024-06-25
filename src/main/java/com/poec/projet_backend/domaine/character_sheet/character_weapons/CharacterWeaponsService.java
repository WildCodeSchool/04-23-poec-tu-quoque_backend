package com.poec.projet_backend.domaine.character_sheet.character_weapons;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class CharacterWeaponsService extends AbstractService<CharacterWeapons> {
    public CharacterWeaponsService(CharacterWeaponsRepository repository) {
        super(repository);
    }

    @Override
    public CharacterWeapons update(Long id, CharacterWeapons entity) {
        return null;
    }
}

