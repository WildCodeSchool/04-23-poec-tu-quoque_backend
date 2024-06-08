package com.poec.projet_backend.domaine.game_table;

import com.poec.projet_backend.domaine.calendar_event.CalendarEventDTO;
import com.poec.projet_backend.domaine.drawing.Drawing;
import com.poec.projet_backend.domaine.drawing.DrawingDTO;
import com.poec.projet_backend.domaine.note.NoteDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterDTO;
import com.poec.projet_backend.domaine.player_character.PlayerCharacterFullDTO;

import java.util.List;

public record GameTableFullDTO(
    Long id,
    String name,
    String Avatar,
    Long userId,
    List<PlayerCharacterDTO> playerCharacterDTOList,
    List<DrawingDTO> drawingList,
    List<CalendarEventDTO> eventList,
    List<NoteDTO> noteList
) {
    public static GameTableFullDTO mapFromEntity(GameTable gameTable) {
        return new GameTableFullDTO(
                gameTable.getId(),
                gameTable.getName(),
                gameTable.getAvatar(),
                gameTable.getUser().getId(),
                gameTable.getPlayerCharacters().stream().map(PlayerCharacterDTO::mapFromEntity).toList(),
                gameTable.getDrawings().stream().map(DrawingDTO::mapFromEntity).toList(),
                gameTable.getCalendarEvents().stream().map(CalendarEventDTO::mapFromEntity).toList(),
                gameTable.getNotes().stream().map(NoteDTO::mapFromEntity).toList()
        );
    }
}
