package com.poec.projet_backend.domaine.player_character;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import com.poec.projet_backend.util.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@RequiredArgsConstructor
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterService service;

    @Autowired
    private UserAppService userAppService;

    @Autowired
    private GameTableService gameTableService;

    @GetMapping("/get/all")
    public ResponseEntity<List<PlayerCharacterDTO>> getAll() {
        List<PlayerCharacter> characterList = service.getAll();
        List<PlayerCharacterDTO> characterDTOList = characterList.stream().map(PlayerCharacterDTO::mapFromEntity).toList();
        return new ResponseEntity<>(characterDTOList, HttpStatus.OK);
    }
    @GetMapping("/get/{characterId}")
    public ResponseEntity<PlayerCharacterFullDTO> getById(@PathVariable("characterId") Long id) {
        PlayerCharacter foundCharacter = service.getById(id);
        PlayerCharacterFullDTO characterFullDTO = PlayerCharacterFullDTO.mapFromEntity(foundCharacter);
        return new ResponseEntity<>(characterFullDTO, HttpStatus.OK);
    }

    @GetMapping("/get/character-available/userId={userId}")
    public ResponseEntity<List<PlayerCharacterDTO>> getAvailableCharacterList(@PathVariable("userId") Long userId) {
        UserApp userFound = userAppService.getById(userId);
        List<PlayerCharacter> availableCharacterList = userFound.getPlayerCharacters().stream().filter(character -> character.getGameTable() == null).toList();
        List<PlayerCharacterDTO> availableCharacterListDTO = availableCharacterList.stream().map(PlayerCharacterDTO::mapFromEntity).toList();
        return new ResponseEntity<>(availableCharacterListDTO, HttpStatus.OK);
    }

    @GetMapping("/get/character-accepted/tableId={tableId}")
    public ResponseEntity<List<PlayerCharacterDTO>> getAcceptedCharacterList(@PathVariable("tableId") Long tableId) {
        GameTable tableFound = gameTableService.getById(tableId);
        List<PlayerCharacter> playerCharacterAcceptedList = tableFound.getPlayerCharacters().stream().filter(PlayerCharacter::isAccepted).toList();
        List<PlayerCharacterDTO> playerCharacterAcceptedDTOList = playerCharacterAcceptedList.stream().map(PlayerCharacterDTO::mapFromEntity).toList();
        return new ResponseEntity<>(playerCharacterAcceptedDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/character-on-hold/tableId={tableId}")
    public ResponseEntity<List<PlayerCharacterShortAvatarDTO>> getOnHoldCharacterList(@PathVariable("tableId") Long tableId) {
        System.out.println(tableId);
        GameTable tableFound = gameTableService.getById(tableId);
        List<PlayerCharacter> playerCharacterOnHoldList = tableFound.getPlayerCharacters().stream().filter(playerCharacter -> !playerCharacter.isAccepted()).toList();
        List<PlayerCharacterShortAvatarDTO> playerCharacterOnHoldDTOList = playerCharacterOnHoldList.stream().map(PlayerCharacterShortAvatarDTO::mapFromEntity).toList();
        return new ResponseEntity<>(playerCharacterOnHoldDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/userId={userId}")
    public ResponseEntity<List<PlayerCharacterDTO>> getByUser(@PathVariable("userId") Long userId) {
        UserApp userFound = userAppService.getById(userId);
        List<PlayerCharacter> userCharacterList = userFound.getPlayerCharacters();
        List<PlayerCharacterDTO> playerCharacterDTOList = userCharacterList.stream().map(PlayerCharacterDTO::mapFromEntity).toList();
        return new ResponseEntity<>(playerCharacterDTOList, HttpStatus.OK);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<PlayerCharacterDTO> add(@RequestBody PlayerCharacter playerCharacter, @PathVariable("userId") Long userId) {
        UserApp foundUser = userAppService.getById(userId);
        playerCharacter.setUser(foundUser);
        PlayerCharacter characterCreated = service.add(playerCharacter);
        PlayerCharacterDTO characterDTO = PlayerCharacterDTO.mapFromEntity(characterCreated);
        return new ResponseEntity<>(characterDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlayerCharacterFullDTO> update(@RequestBody PlayerCharacter playerCharacter, @PathVariable Long id) {
        PlayerCharacter updatedCharacter = service.update(id, playerCharacter);
        PlayerCharacterFullDTO characterFullDTO = PlayerCharacterFullDTO.mapFromEntity(updatedCharacter);
        return new ResponseEntity<>(characterFullDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("id") Long id) {
        System.out.println(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<PlayerCharacterFullDTO> patchCharacter(@RequestBody PlayerCharacter incompleteCharacter, @PathVariable Long id) {
        PlayerCharacter foundCharacter = service.getById(id);

        try {
            Patcher.elementPatcher(foundCharacter, incompleteCharacter);
            service.add(foundCharacter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(PlayerCharacterFullDTO.mapFromEntity(foundCharacter), HttpStatus.OK);
    }
}
