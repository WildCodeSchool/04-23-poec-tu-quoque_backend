package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationFixtures {

    @Autowired
    private GameTableService tableService;
    @Autowired
    private UserAppService userService;

    public void load() {
        generate();
    }

    public void generate() {
        tableService.addInvitation(4L, 1L);
        tableService.addInvitation(5L, 1L);
        tableService.addInvitation(3L, 4L);
        tableService.addInvitation(3L, 2L);
    }
}
