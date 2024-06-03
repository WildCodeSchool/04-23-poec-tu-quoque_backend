package com.poec.projet_backend.domaine.game_table;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameTableService extends AbstractService<GameTable> {
    public GameTableService(GameTableRepository repository) {
        super(repository);
    }

    @Override
    public GameTable update(Long id, GameTable entity) {
        GameTable foundTable = getById(id);
        foundTable.setAvatar(entity.getAvatar());
        foundTable.setName(entity.getName());
        return repository.save(foundTable);
    }
}
