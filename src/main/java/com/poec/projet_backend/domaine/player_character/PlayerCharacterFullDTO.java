package com.poec.projet_backend.domaine.player_character;

import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableDTO;

public record PlayerCharacterFullDTO(
    Long id,
    String name,
    String avatar,
    Boolean accepted,
    GameTableDTO gameTable,
    Long characterSheetId
    ) {
    public static PlayerCharacterFullDTO mapFromEntity(PlayerCharacter playerCharacter) {
        return new PlayerCharacterFullDTO(
            playerCharacter.getId(),
            playerCharacter.getName(),
            playerCharacter.getAvatar(),
            playerCharacter.isAccepted(),
            playerCharacter.getGame_table() != null ? GameTableDTO.mapFromEntity(playerCharacter.getGame_table()) : null,
            playerCharacter.getCharacterSheet() != null ? playerCharacter.getCharacterSheet().getId() : null

        );
    }

}
