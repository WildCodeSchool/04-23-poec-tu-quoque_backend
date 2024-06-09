package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableDTO;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.note.NoteDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterDTO;

import java.util.List;


public record UserAppDTO(
        Long id,
        String nickname,
        String email,
        String avatar,
        List<PlayerCharacterDTO> playerCharacterList,
        List<NoteDTO> playerNoteList,
        List<GameTableDTO> playerGameTableList

) {
    public static UserAppDTO mapFromEntity(UserApp userApp){
        List<PlayerCharacterDTO> playerCharacterList = userApp.getPlayer_characters().stream()
                .map(PlayerCharacterDTO::mapFromEntity)
                .toList();

        List<NoteDTO> playerNoteList = userApp.getNotes().stream()
                .map(NoteDTO::mapFromEntity)
                .toList();

        List<GameTableDTO> playerGameTableList = userApp.getGame_tables().stream()
                .map(GameTableDTO::mapFromEntity)
                .toList();

        return new UserAppDTO(
                userApp.getId(),
                userApp.getNickname(),
                userApp.getEmail(),
                userApp.getAvatar(),
                playerCharacterList,
                playerNoteList,
                playerGameTableList
        );
    }
}
