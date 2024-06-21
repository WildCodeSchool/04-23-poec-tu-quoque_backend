package com.poec.projet_backend.domaine.player_character;

public record PlayerCharacterShortAvatarDTO(
        Long id,
        String nickname,
        String avatar
) {
    public static PlayerCharacterShortAvatarDTO mapFromEntity (PlayerCharacter playerCharacter) {
        return new PlayerCharacterShortAvatarDTO(
                playerCharacter.getId(),
                playerCharacter.getName(),
                playerCharacter.getAvatar()
        );
    }
}
