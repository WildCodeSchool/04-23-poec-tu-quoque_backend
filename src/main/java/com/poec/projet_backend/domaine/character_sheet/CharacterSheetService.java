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

    @Autowired
    PlayerCharacterService characterService;

    public CharacterSheetService(CharacterSheetRepository repository) {
        super(repository);
    }

    @Override
    public CharacterSheet update(Long id, CharacterSheet entity) {
        return null;
    }

    public CharacterSheetDTO update(Long id, CharacterSheetDTO sheetDTO) {
        PlayerCharacter character = characterService.getCharacterByCharacterSheet(sheetDTO.id());
        CharacterSheet sheet = CharacterSheetDTO.mapFromDtoToEntityWithoutCharacterPlayer(sheetDTO, character);


        //foreach skill : verify if skill exists in db
        //      if true -> update
        //      else -> create

        // foreach weapon : verify if weapon exists in db
        //      if true -> update
        //      else -> create
        //save

        return CharacterSheetDTO.mapFromEntity(sheet);
    }
}
