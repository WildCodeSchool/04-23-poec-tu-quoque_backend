package com.poec.projet_backend.domaine.game_table;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class GameTableController extends AbstractController<GameTable> {

    @Autowired
    private GameTableService service;

    @Autowired
    private UserAppService userAppService;

    @PostMapping("/addTable/{userId}")
    public ResponseEntity<GameTableDTO> add(@RequestBody GameTable gameTable, @PathVariable("userId") Long userId) {
        UserApp foundUser = userAppService.getById(userId);
        gameTable.setUser(foundUser);
        GameTable tableCreated = service.add(gameTable);
        GameTableDTO dto = GameTableDTO.mapFromEntity(tableCreated);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
