package com.poec.projet_backend.util;

import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppInitializerDTO;
import com.poec.projet_backend.user_app.UserAppService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFixtures {

    @Autowired
    private UserAppService userService;
    @Autowired
    private PasswordEncoder encoder;

    public void load() {
        if( userService.getAll().size() < 7 ) {
            for (UserApp user: generate()) {
                this.userService.add(user);
            }
        }
    }

    private List<UserApp> generate() {
        List<UserAppInitializerDTO> userDtoList= generateUserDTOList();
        List<UserApp> userList = userDtoList.stream().map(UserAppInitializerDTO::mapFromInitializerToUser).toList();
        for(UserApp user: userList ) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return userList;
    }

    private List<UserAppInitializerDTO> generateUserDTOList() {
        UserAppInitializerDTO user1 = new UserAppInitializerDTO("SkyWalker22",
                "skywalker22@gmail.com",
                "Bonjour33!",
                "/assets/images/user-profile-images/user1.jpg");

        UserAppInitializerDTO user2 = new UserAppInitializerDTO(
                "Moussay",
                "moussay@gmail.com",
                "Bonjour33!",
                "/assets/images/user-profile-images/user2.png"
         );

        UserAppInitializerDTO user3 = new UserAppInitializerDTO(
                "SkyIsTheLimit",
                "SkyIsTheLimit@gmail.com",
                "Bonjour33!",
                "/assets/images/user-profile-images/user1.jpg"
         );

        UserAppInitializerDTO user4 = new UserAppInitializerDTO(
                "MoussaillonDeMalheur",
                "MoussaillonDeMalheur@gmail.com",
                "Bonjour33!",
                "/assets/images/user-profile-images/user2.png"
         );

        UserAppInitializerDTO user5 = new UserAppInitializerDTO(
                "MimirOfMimisbrunn",
                "MimirOfMimisbrunn@gmail.com",
                "Bonjour33!",
                "/assets/images/user-profile-images/user2.png"
         );

        UserAppInitializerDTO user6 = new UserAppInitializerDTO(
                "Sigyn33",
                "Sigyn33@gmail.com",
                "_=",
                "/assets/images/user-profile-images/user1.jpg"
         );
        List<UserAppInitializerDTO> DTOList = new ArrayList<>();
        DTOList.add(user1);
        DTOList.add(user2);
        DTOList.add(user3);
        DTOList.add(user4);
        DTOList.add(user5);
        DTOList.add(user6);

        return DTOList;
    }
}
