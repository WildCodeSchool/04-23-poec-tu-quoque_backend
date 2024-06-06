package com.poec.projet_backend.domaine.drawing;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterDTO;
import com.poec.projet_backend.user_app.UserApp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drawings")
@RequiredArgsConstructor
public class DrawingController extends AbstractController<Drawing> {

    @Autowired
    private DrawingService service;

    @Autowired
    private GameTableService gameTableService;

    @GetMapping("/get/all")
    public ResponseEntity<List<DrawingDTO>> getAll() {
        List<Drawing> drawingList = service.getAll();
        List<DrawingDTO> drawingDTOList = drawingList.stream().map(DrawingDTO::mapFromEntity).toList();
        return new ResponseEntity<>(drawingDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/{drawingId}")
    public ResponseEntity<DrawingDTO> getById(@PathVariable("drawingId") Long drawingId) {
        Drawing foundDrawing = service.getById(drawingId);
        DrawingDTO drawingDTO = DrawingDTO.mapFromEntity(foundDrawing);
        return new ResponseEntity<>(drawingDTO, HttpStatus.OK);
    }

    @PostMapping("/add/{tableId}")
    public ResponseEntity<DrawingDTO> add(@RequestBody Drawing drawing, @PathVariable("tableId") Long tableId) {
        GameTable foundGameTable = gameTableService.getById(tableId);
        drawing.setGame_table(foundGameTable);
        Drawing drawingCreated = service.add(drawing);
        DrawingDTO drawingDTO = DrawingDTO.mapFromEntity(drawingCreated);
        return new ResponseEntity<>(drawingDTO, HttpStatus.OK);
    }

}
