package com.poec.projet_backend.domaine.character_sheet;

import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.CharacterStatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sheets")
public class CharacterSheetController extends AbstractController<CharacterSheet> {
    @Autowired
    private CharacterSheetService sheetService;


    @GetMapping("/get/{id}")
    public ResponseEntity<CharacterSheetDTO> getById(@PathVariable("id") Long id) {
        CharacterSheet foundSheet = sheetService.getById(id);
        CharacterSheetDTO sheetDTO = CharacterSheetDTO.mapFromEntity(foundSheet);
        return new ResponseEntity<>(sheetDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CharacterSheetDTO> update(@PathVariable("id") Long id, @RequestBody CharacterSheetDTO sheetDTO) {
        return new ResponseEntity<CharacterSheetDTO>(sheetService.update(id, sheetDTO), HttpStatus.OK);
    }
}
