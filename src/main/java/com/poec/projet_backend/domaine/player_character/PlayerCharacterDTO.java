package com.poec.projet_backend.domaine.player_character;

public record PlayerCharacterDTO(
        Long id,
        String name
        ) {
    public static PlayerCharacterDTO mapFromEntity (PlayerCharacter playerCharacter) {
        return new PlayerCharacterDTO(
                playerCharacter.getId(),
                playerCharacter.getName()
        );
    }
}
