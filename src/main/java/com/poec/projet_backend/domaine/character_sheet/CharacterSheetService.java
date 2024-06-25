package com.poec.projet_backend.domaine.character_sheet;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class CharacterSheetService extends AbstractService<CharacterSheet> {
    public CharacterSheetService(CharacterSheetRepository repository) {
        super(repository);
    }

    @Override
    public CharacterSheet update(Long id, CharacterSheet entity) {
        return null;
    }
}
