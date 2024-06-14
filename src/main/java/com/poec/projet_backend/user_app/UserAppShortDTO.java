package com.poec.projet_backend.user_app;

public record UserAppShortDTO(
        Long id,
        String nickname,
        String avatar
) {
    public static UserAppShortDTO mapFromEntity(UserApp userApp) {
        return new UserAppShortDTO(
                userApp.getId(),
                userApp.getNickname(),
                userApp.getAvatar()
        );
    }
}
