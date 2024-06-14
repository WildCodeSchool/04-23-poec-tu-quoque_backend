package com.poec.projet_backend.domaine.calendar_event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private PlayerCharacter playerCharacter;

    @ManyToOne
    @JoinColumn(name = "game_table_id")
    @JsonIgnoreProperties("calendar_events")
    private GameTable gameTable;

    CalendarEvent(
            String title,
            String description,
            Date start,
            Date end,
            boolean allDay,
            String color) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.color = color;
    }

    CalendarEvent(
            String title,
            String description,
            Date start,
            boolean allDay) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.allDay = allDay;
    }

    CalendarEvent(
            String title,
            Date start,
            boolean allDay) {
        this.title = title;
        this.start = start;
        this.allDay = allDay;
    }
}
