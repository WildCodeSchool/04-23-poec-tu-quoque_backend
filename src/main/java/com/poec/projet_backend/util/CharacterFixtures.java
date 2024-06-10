package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.character_sheet.CharacterSheet;
import com.poec.projet_backend.domaine.character_sheet.CharacterSheetService;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterFixtures {

    @Autowired
    private PlayerCharacterService characterService;
    @Autowired
    private UserAppService userService;
    @Autowired
    private GameTableService tableService;
    @Autowired
    private CharacterSheetService sheetService;
    @Autowired SheetFixture sheetFixture;

    public PlayerCharacter loadNico() {
        if(characterService.getAll().isEmpty()) {
            UserApp user = userService.getById(1L);
            PlayerCharacter character = PlayerCharacter.builder()
                    .name("Nico")
                    .avatar("Bob")
                    .user(user)
                    .characterSheet(sheetFixture.load())
                    .build();
            return this.characterService.add(character);
        }
        return null;
    }

    public void load() {
        for(PlayerCharacter character: generate()) {
            CharacterSheet sheet = character.getCharacterSheet();
            this.characterService.add(character);
            sheetService.update(sheet.getId(), sheet);

        }
    }

    private List<PlayerCharacter> generate() {
        List<PlayerCharacter> characterList = new ArrayList<>();

        PlayerCharacter character1 = PlayerCharacter.builder()
                .name("Elric")
                .avatar("/assets/images/user-profile-images/character-elric.jpg")
                .user(userService.getById(3L))
                .game_table(tableService.getById(4L))
                .accepted(false)
                //"scheduleId": 1
                .build();
        characterService.setStartingSheet(character1);
        characterList.add(character1);

        System.err.println(character1.getCharacterSheet().getCharacterName());

        PlayerCharacter character2 = PlayerCharacter.builder()
                .name("Gandalf")
                .avatar("/assets/images/user-profile-images/user3.png")
                .user(userService.getById(3L))
                .game_table(tableService.getById(2L))
                .accepted(true)
                .build();
        characterService.setStartingSheet(character2);
        characterList.add(character2);

        PlayerCharacter character3 = PlayerCharacter.builder()
                .name("Elrond")
                .avatar("/assets/images/user-profile-images/character-elrond.jpg")
                .user(userService.getById(3L))
                .accepted(false)
                .build();
        characterService.setStartingSheet(character3);
        characterList.add(character3);

        PlayerCharacter character4 = PlayerCharacter.builder()
                .name("Gimli")
                .avatar("/assets/images/user-profile-images/user3.png")
                .user(userService.getById(4L))
                .accepted(true)
                .build();
        characterService.setStartingSheet(character4);
        characterList.add(character4);

        PlayerCharacter character5 = PlayerCharacter.builder()
                .name("Freyr")
                .avatar("")
                .user(userService.getById(5L))
                .game_table(tableService.getById(1L))
                .accepted(true)
                .build();
        characterService.setStartingSheet(character5);
        characterList.add(character5);

        PlayerCharacter character6 = PlayerCharacter.builder()
                .name("Néo")
                .avatar("/assets/images/user-profile-images/user1.jpg")
                .user(userService.getById(6L))
                .game_table(tableService.getById(1L))
                .accepted(false)
                .build();
        characterService.setStartingSheet(character6);
        characterList.add(character6);

        PlayerCharacter character7 = PlayerCharacter.builder()
                .name("Morgoth")
                .avatar("")
                .user(userService.getById(3L))
                .accepted(false)
                .build();
        characterService.setStartingSheet(character7);
        characterList.add(character7);

        return characterList;
    }
}
