package com.poec.projet_backend.domaine.player_character;

import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@RequiredArgsConstructor
public class PlayerCharacterController extends AbstractController<PlayerCharacter> {

    @Autowired
    private PlayerCharacterService service;

    @Autowired
    private UserAppService userAppService;

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

    @GetMapping("/get/userId={userId}")
    public ResponseEntity<List<PlayerCharacterDTO>> getByUser(@PathVariable("userId") Long userId) {
        UserApp userFound = userAppService.getById(userId);
        List<PlayerCharacter> userCharacterList = userFound.getPlayer_characters();
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
}
