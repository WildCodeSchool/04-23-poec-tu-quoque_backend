package com.poec.projet_backend.domaine.game_table;
import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameTableService extends AbstractService<GameTable> {

    @Autowired
    private UserAppService userAppService;

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

    public GameTable addInvitation(Long userId, Long gameTableId) {
        GameTable table = getById(gameTableId);
        UserApp user = userAppService.getById(userId);

        table.addInvitation(user);

        return repository.save(table);
    }
}
