package com.poec.projet_backend.domaine.player_character;
import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheetService;
import com.poec.projet_backend.user_app.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(entity.getGameTable() != null) {
            foundCharacter.setGameTable(entity.getGameTable());
        } else {
            foundCharacter.setGameTable(null);
        }
        return repository.save(foundCharacter);
    }

    public PlayerCharacter setStartingSheet(PlayerCharacter entity) {
        CharacterSheet sheet = CharacterSheet.createBlank();
        sheet.setCharacterName(entity.getName());
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

    public PlayerCharacter getCharacterByCharacterSheet(Long sheetId) {
        List<PlayerCharacter> characterList = getAll();
        Optional<PlayerCharacter> characterMatch =  characterList.stream()
                .filter(character -> Objects.equals(character.getCharacterSheet().getId(), sheetId))
                .findFirst();

        return characterMatch.orElseThrow();
    }
}
