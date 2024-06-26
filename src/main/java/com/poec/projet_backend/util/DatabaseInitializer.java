package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheetService;
import com.poec.projet_backend.domaine.note.NoteService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.user_app.Role;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final SheetFixture sheetFixture;
    private final CharacterFixtures characterFixtures;
    private final UserFixtures userFixtures;
    private final TableFixtures tableFixtures;
    private final CharacterSheetService sheetService;
    private final InvitationFixtures invitationFixtures;
    private final EventFixtures eventFixtures;
    private final NotesFixtures notesFixtures;


    @Override
    public void run(String... args) throws Exception {
        if(this.userAppRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdmin();
            this.createUsers();
            tableFixtures.load();
            characterFixtures.load();
            invitationFixtures.load();
            eventFixtures.load();
            notesFixtures.load();
        }
    }

    private void createAdmin() {
        UserApp admin = UserApp.builder()
                .nickname("admin")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_" + Role.ADMIN)
                .build();

        this.userAppRepository.save(admin);
    }

    private void createUsers() {
        UserApp user1 = UserApp.builder()
                .nickname("user1")
                .email("user1@user1.com")
                .avatar("https://i.postimg.cc/3rX9Hd2j/fantasy-rama-navami-celebration.jpg")
                .password(passwordEncoder.encode("user1"))
                .role("ROLE_" + Role.USER)
                .build();

        this.userAppRepository.save(user1);
        userFixtures.load();
        createSheet();
    }

    private void createSheet() {
        PlayerCharacter player =  characterFixtures.loadNico();

    }
}
