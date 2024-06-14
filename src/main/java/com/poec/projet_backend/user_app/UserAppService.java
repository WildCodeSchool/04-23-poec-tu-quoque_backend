package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import com.poec.projet_backend.domaine.game_table.GameTable;
import org.springframework.stereotype.Service;

@Service
public class UserAppService extends AbstractService<UserApp> {
    public UserAppService(UserAppRepository repository) {
        super(repository);
    }

    @Override
    public UserApp update(Long id, UserApp entity) {
        return null;
    }

    public UserApp addNewGameTable(Long userId, GameTable gameTable) {
        UserApp userAppFound = getById(userId);
        userAppFound.getGameTables().add(gameTable);
        repository.save(userAppFound);
        return userAppFound;
    }
}
