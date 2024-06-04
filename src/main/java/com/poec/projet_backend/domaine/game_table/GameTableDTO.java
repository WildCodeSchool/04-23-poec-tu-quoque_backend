package com.poec.projet_backend.domaine.game_table;

public record GameTableDTO(
        Long id,
        String name,
        Long userId
) {
    public static GameTableDTO mapFromEntity(GameTable table) {
        return new GameTableDTO(
                table.getId(),
                table.getName(),
                table.getUser().getId()
        );
    }
}
