package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.user_app.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableFixtures {

    @Autowired
    private GameTableService tableService;
    @Autowired
    private UserAppService userService;

    public void load() {
        for (GameTable table: generateTables()) {
            tableService.add(table);
        }
    }

    private List<GameTable> generateTables() {
        List<GameTable> tableList = new ArrayList<>();
        GameTable table1 = GameTable.builder()
                .avatar("/assets/images/table-profile-images/le-bois-de-la-misericorde.jpg")
                .name("Le bois de la miséricorde")
                .user(userService.getById(3L))
                .build();
        tableList.add(table1);

        GameTable table2 = GameTable.builder()
                .avatar("/assets/images/table-profile-images/la-communaute.jpg")
                .name("La communauté")
                .user(userService.getById(5L))
                .build();
        tableList.add(table2);

        GameTable table3 = GameTable.builder()
                .avatar("/assets/images/table-profile-images/le-donjon-des-monts-venteux.jpg")
                .name("Le donjon des monts venteux")
                .user(userService.getById(3L))
                .build();
        tableList.add(table3);

        GameTable table4 = GameTable.builder()
                .avatar("/assets/images/table-profile-images/les-catacombes-oubliees.jpg")
                .name("Les catacombes oubliées")
                .user(userService.getById(4L))
                .build();
        tableList.add(table4);

        GameTable table5 = GameTable.builder()
                .name("La marche de l'Ouest")
                .user(userService.getById(3L))
                .build();
        tableList.add(table5);

        return tableList;
    }
}
