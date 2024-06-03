package com.poec.projet_backend.domaine.game_table;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class GameTableController extends AbstractController<GameTable> {

    @Autowired
    private GameTableService service;

}
