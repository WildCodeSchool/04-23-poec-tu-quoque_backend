package com.poec.projet_backend.domaine.player_character;

import com.poec.projet_backend.domaine.game_table.GameTableDTO;
import com.poec.projet_backend.domaine.note.NoteDTO;

import java.util.List;

public record PlayerCharacterFullDTO(
    Long id,
    String name,
    String avatar,
    Boolean accepted,
    GameTableDTO gameTable,
    Long characterSheetId,
    List<NoteDTO> characterNoteList
    ) {
    public static PlayerCharacterFullDTO mapFromEntity(PlayerCharacter playerCharacter) {
        return new PlayerCharacterFullDTO(
            playerCharacter.getId(),
            playerCharacter.getName(),
            playerCharacter.getAvatar(),
            playerCharacter.isAccepted(),
            playerCharacter.getGameTable() != null ? GameTableDTO.mapFromEntity(playerCharacter.getGameTable()) : null,
            playerCharacter.getCharacterSheet() != null ? playerCharacter.getCharacterSheet().getId() : null,
            playerCharacter.getNotes().stream().map(NoteDTO::mapFromEntity).toList()

        );
    }

}
