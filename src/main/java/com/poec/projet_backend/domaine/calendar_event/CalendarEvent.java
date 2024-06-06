package com.poec.projet_backend.domaine.calendar_event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date start;
    private Date end;
    private boolean allDay;
    private String color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_character_id")
    @JsonIgnoreProperties("calendar_events")
    private PlayerCharacter player_character;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "game_table_id")
//    @JsonIgnoreProperties("calendar_event")
//    private GameTable game_table;

    @ManyToOne
    @JoinColumn(name = "game_table_id")
    @JsonIgnoreProperties("calendar_events")
    private GameTable game_table;
}
