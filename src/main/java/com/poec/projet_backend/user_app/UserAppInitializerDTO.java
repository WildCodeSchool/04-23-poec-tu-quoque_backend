package com.poec.projet_backend.user_app;

public record UserAppInitializerDTO(
        String nickname,
        String email,
        String password,
        String avatar
) {
    public static UserApp mapFromInitializerToUser(UserAppInitializerDTO initializerDTO) {
        return UserApp.builder()
                .nickname(initializerDTO.nickname)
                .email(initializerDTO.email)
                .password(initializerDTO.password)
                .avatar(initializerDTO.avatar)
                .role("ROLE_" + Role.USER)
                .build();
    }
}
