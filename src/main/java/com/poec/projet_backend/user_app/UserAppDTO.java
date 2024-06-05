package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;

import java.util.List;


public record UserAppDTO(
        Long id,
        String nickname,
        String email,
        String avatar,
        List<Long> playerCharacterIds,
        List<Long> notesIds,
        List<Long> gameTableIds

) {
    public static UserAppDTO mapFromEntity(UserApp userApp){
        List<Long> playerCharacterIds = userApp.getPlayer_characters().stream()
                .map(PlayerCharacter::getId)
                .toList();

        List<Long> notesIds = userApp.getNotes().stream()
                .map(Note::getId)
                .toList();

        List<Long> gameTableIds = userApp.getGame_tables().stream()
                .map(GameTable::getId)
                .toList();
        return new UserAppDTO(
                userApp.getId(),
                userApp.getNickname(),
                userApp.getEmail(),
                userApp.getAvatar(),
                playerCharacterIds,
                notesIds,
                gameTableIds
        );
    }
}
