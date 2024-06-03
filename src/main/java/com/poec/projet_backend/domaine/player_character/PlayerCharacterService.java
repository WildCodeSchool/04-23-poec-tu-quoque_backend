package com.poec.projet_backend.domaine.player_character;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

public class PlayerCharacterService extends AbstractService<PlayerCharacter> {


    public PlayerCharacterService(PlayerCharacterRepository repository) {
        super(repository);
    }

    @Override
    public PlayerCharacter update(Long id, PlayerCharacter entity) {
        PlayerCharacter foundCharacter = getById(id);
        foundCharacter.setName(entity.getName());
        foundCharacter.setAvatar(entity.getAvatar());
        foundCharacter.setAccepted(entity.isAccepted());
        foundCharacter.setGame_table(entity.getGame_table());
        return repository.save(foundCharacter);
    }
}
