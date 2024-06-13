package com.poec.projet_backend.domaine.player_character;
import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerCharacterService extends AbstractService<PlayerCharacter> {

    @Autowired
    private CharacterSheetService sheetService;

    public PlayerCharacterService(PlayerCharacterRepository repository) {
        super(repository);
    }

    @Override
    public PlayerCharacter update(Long id, PlayerCharacter entity) {
        PlayerCharacter foundCharacter = getById(id);
        foundCharacter.setName(entity.getName());
        foundCharacter.setAvatar(entity.getAvatar());
        foundCharacter.setAccepted(entity.isAccepted());
        if(entity.getGame_table() != null) {
            foundCharacter.setGame_table(entity.getGame_table());
        } else {
            foundCharacter.setGame_table(null);
        }
        return repository.save(foundCharacter);
    }

    public PlayerCharacter setStartingSheet(PlayerCharacter entity) {
        CharacterSheet sheet = CharacterSheet.createBlank();
        sheetService.add(sheet);
        entity.setCharacterSheet(sheet);
        return entity;
    }

    public String getUserNameByCharacterSheet(Long sheetId) {
        List<PlayerCharacter> characterList = getAll();
        Optional<PlayerCharacter> characterMatch =  characterList.stream()
                .filter(character -> Objects.equals(character.getCharacterSheet().getId(), sheetId))
                .findFirst();

        return characterMatch.orElseThrow().getUser().getNickname();
    }
}
