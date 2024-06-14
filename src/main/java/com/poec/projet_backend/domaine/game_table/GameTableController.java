package com.poec.projet_backend.domaine.game_table;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterDTO;
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
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class GameTableController extends AbstractController<GameTable> {

    @Autowired
    private GameTableService service;

    @Autowired
    private UserAppService userAppService;

    @Autowired
    Patcher patcher;

    @GetMapping("/get/all")
    public ResponseEntity<List<GameTableDTO>> getAll() {
        List<GameTable> gameTableList = service.getAll();
        List<GameTableDTO> gameTableDTOList = gameTableList.stream().map(GameTableDTO::mapFromEntity).toList();
        return new ResponseEntity<>(gameTableDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/{tableId}")
    public ResponseEntity<GameTableFullDTO> getById(@PathVariable("tableId") Long tableId) {
        GameTable foundTable = service.getById(tableId);
        GameTableFullDTO tableFullDTO = GameTableFullDTO.mapFromEntity(foundTable);
        return new ResponseEntity<>(tableFullDTO, HttpStatus.OK);
    }


    @GetMapping("/get/userId={userId}")
    public ResponseEntity<List<GameTableDTO>> getByUser(@PathVariable("userId") Long userId) {
        UserApp userFound = userAppService.getById(userId);
        List<GameTable> userTableList = userFound.getGame_tables();
        List<GameTableDTO> gameTableDTOList = userTableList.stream().map(GameTableDTO::mapFromEntity).toList();
        return new ResponseEntity<>(gameTableDTOList, HttpStatus.OK);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<GameTableDTO> add(@RequestBody GameTable gameTable, @PathVariable("userId") Long userId) {
        UserApp foundUser = userAppService.getById(userId);
        gameTable.setUser(foundUser);
        GameTable tableCreated = service.add(gameTable);
        GameTableDTO dto = GameTableDTO.mapFromEntity(tableCreated);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<GameTableFullDTO> patchTable(@RequestBody GameTable incompleteTable, @PathVariable("id") Long id) {
        GameTable foundTable = service.getById(id);

        try {
            Patcher.elementPatcher(foundTable, incompleteTable);
            service.add(foundTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(GameTableFullDTO.mapFromEntity(foundTable), HttpStatus.OK);
    }

}
