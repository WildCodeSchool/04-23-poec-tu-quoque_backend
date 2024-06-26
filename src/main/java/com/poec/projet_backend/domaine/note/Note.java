package com.poec.projet_backend.domaine.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String text;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_table_id")
    @JsonIgnoreProperties("notes")
    private GameTable gameTable;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_character_id")
    @JsonIgnoreProperties("notes")
    private PlayerCharacter playerCharacter;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("notes")
    private UserApp user;
}
