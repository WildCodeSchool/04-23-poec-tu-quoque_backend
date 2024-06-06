package com.poec.projet_backend.domaine.game_table;

import com.poec.projet_backend.domaine.drawing.Drawing;
import com.poec.projet_backend.domaine.drawing.DrawingDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterFullDTO;

import java.util.List;

public record GameTableFullDTO(
    Long id,
    String name,
    String Avatar,
    List<PlayerCharacterDTO> playerCharacterDTOList,
    List<DrawingDTO> drawingList
) {
    public static GameTableFullDTO mapFromEntity(GameTable gameTable) {
        return new GameTableFullDTO(
                gameTable.getId(),
                gameTable.getName(),
                gameTable.getAvatar(),
                gameTable.getPlayerCharacters().stream().map(PlayerCharacterDTO::mapFromEntity).toList(),
                gameTable.getDrawings().stream().map(DrawingDTO::mapFromEntity).toList()
        );
    }
}