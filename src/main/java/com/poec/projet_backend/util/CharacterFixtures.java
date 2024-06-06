package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterFixtures {

    @Autowired
    private PlayerCharacterService service;
    @Autowired
    private UserAppService userService;

    public PlayerCharacter load() {
        if(service.getAll().isEmpty()) {
            UserApp user = userService.getById(1L);
            PlayerCharacter player = PlayerCharacter.builder()
                    .name("Nico")
                    .avatar("Bob")
                    .user(user)
                    .build();
            return this.service.add(player);
        }
        return null;
    }
}
