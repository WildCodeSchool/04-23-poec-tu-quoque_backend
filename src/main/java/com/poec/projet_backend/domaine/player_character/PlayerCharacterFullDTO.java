package com.poec.projet_backend.domaine.player_character;

public record PlayerCharacterFullDTO(
    Long id,
    String name,
    String avatar,
    Boolean accepted,
    Long gameTableId,
    Long characterSheetId
    ) {
    public static PlayerCharacterFullDTO mapFromEntity(PlayerCharacter playerCharacter) {
        return new PlayerCharacterFullDTO(
            playerCharacter.getId(),
            playerCharacter.getName(),
            playerCharacter.getAvatar(),
            playerCharacter.isAccepted(),
            playerCharacter.getGame_table() != null ? playerCharacter.getGame_table().getId() : null,
            playerCharacter.getCharacter_sheet() != null ? playerCharacter.getCharacter_sheet().getId() : null

        );
    }

}
