package com.poec.projet_backend.domaine.character_sheet;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
