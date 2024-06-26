package com.poec.projet_backend.domaine.game_table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domaine.calendar_event.CalendarEvent;
import com.poec.projet_backend.domaine.drawing.Drawing;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.player_character.PlayerCharacter;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;

    @OneToMany(mappedBy = "gameTable")
    @JsonIgnoreProperties("game_table")
    private List<PlayerCharacter> playerCharacters;

    @OneToMany(mappedBy = "gameTable", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("game_table")
    private List<Drawing> drawings;

    @OneToMany(mappedBy = "gameTable")
    @JsonIgnoreProperties("game_table")
    private List<Note> notes;

    @OneToMany(mappedBy = "gameTable", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("game_table")
    private List<CalendarEvent> calendarEvents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("game_tables")
    private UserApp user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_table_invitations",
            joinColumns = @JoinColumn(name = "game_table_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("game_tables_invitation")
    private List<UserApp> usersInvitation = new ArrayList<>();

    GameTable(String avatar, String name, UserApp user) {
        this.avatar = avatar;
        this.name = name;
        this.user = user;
    }

    GameTable(String name, UserApp user) {
        this.name = name;
        this.user = user;
    }

    public void addInvitation(UserApp user) {
        this.usersInvitation.add(user);
    }
}
