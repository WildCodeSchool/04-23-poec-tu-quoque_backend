package com.poec.projet_backend.domaine.calendar_event;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.domaine.note.Note;
import com.poec.projet_backend.domaine.note.NoteDTO;
import com.poec.projet_backend.util.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class CalendarEventController extends AbstractController<CalendarEvent> {

    @Autowired
    private CalendarEventService service;

    @Autowired
    private GameTableService gameTableService;

    @GetMapping("/get/all")
    public ResponseEntity<List<CalendarEventDTO>> getAll() {
        List<CalendarEvent> calendarEventList = service.getAll();
        List<CalendarEventDTO> calendarEventDTOList = calendarEventList.stream().map(CalendarEventDTO::mapFromEntity).toList();
        return new ResponseEntity<>(calendarEventDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/tableId={tableId}")
    public ResponseEntity<List<CalendarEventDTO>> getByTable(@PathVariable("tableId") Long tableId) {
        GameTable foundTable = gameTableService.getById(tableId);
        List<CalendarEvent> calendarEventList = foundTable.getCalendarEvents();
        List<CalendarEventDTO> calendarEventDTOList = calendarEventList.stream().map(CalendarEventDTO::mapFromEntity).toList();
        return new ResponseEntity<>(calendarEventDTOList, HttpStatus.OK);
    }

    @PostMapping("/add/{tableId}")
    public ResponseEntity<CalendarEventDTO> add(@RequestBody CalendarEvent calendarEvent, @PathVariable("tableId") Long tableId) {
        GameTable foundGameTable = gameTableService.getById(tableId);
        calendarEvent.setGame_table(foundGameTable);
        CalendarEvent eventCreated = service.add(calendarEvent);
        CalendarEventDTO calendarEventDTO = CalendarEventDTO.mapFromEntity(eventCreated);
        return new ResponseEntity<>(calendarEventDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<CalendarEventDTO> patchEvent(@RequestBody CalendarEvent incompleteEvent, @PathVariable("id") Long id) {
        CalendarEvent foundEvent = service.getById(id);

        try {
            Patcher.elementPatcher(foundEvent, incompleteEvent);
            service.add(foundEvent);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(CalendarEventDTO.mapFromEntity(foundEvent), HttpStatus.OK);
    }
}
